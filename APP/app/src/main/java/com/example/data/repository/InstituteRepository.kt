package com.example.data.repository

import android.content.Context
import com.example.config.AppConfig
import com.example.data.local.AppDatabase
import com.example.data.local.CachedDataEntity
import com.example.data.model.BannerItem
import com.example.data.model.Course
import com.example.data.model.Faculty
import com.example.data.model.GalleryItem
import com.example.data.model.InstituteSettings
import com.example.data.model.Notice
import com.example.data.model.ResultItem
import com.example.data.remote.JsonParser
import com.example.data.remote.NetworkClient
import com.example.data.sample.DefaultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

data class SearchResult(
    val courses: List<Course> = emptyList(),
    val notices: List<Notice> = emptyList(),
    val faculty: List<Faculty> = emptyList(),
    val results: List<ResultItem> = emptyList()
) {
    val totalCount: Int get() = courses.size + notices.size + faculty.size + results.size
    val isEmpty: Boolean get() = totalCount == 0
}

data class RepoState<T>(
    val data: T,
    val isLoading: Boolean = false,
    val isFromCache: Boolean = true,
    val lastUpdated: Long = 0L,
    val errorMessage: String? = null
)

class InstituteRepository(private val context: Context) {

    private val db = AppDatabase.getInstance(context)
    private val cacheDao = db.cacheDao()

    private val prefs = context.getSharedPreferences("medha_mantra_prefs", Context.MODE_PRIVATE)
    private val KEY_BASE_URL = "custom_github_base_url"

    private val _baseUrl = MutableStateFlow(
        prefs.getString(KEY_BASE_URL, AppConfig.DEFAULT_GITHUB_BASE_URL) ?: AppConfig.DEFAULT_GITHUB_BASE_URL
    )
    val baseUrl: StateFlow<String> = _baseUrl.asStateFlow()

    // State flows for each resource
    private val _coursesState = MutableStateFlow(
        RepoState<List<Course>>(data = JsonParser.parseCourses(DefaultData.COURSES_JSON) ?: emptyList())
    )
    val coursesState: StateFlow<RepoState<List<Course>>> = _coursesState.asStateFlow()

    private val _noticesState = MutableStateFlow(
        RepoState<List<Notice>>(data = JsonParser.parseNotices(DefaultData.NOTICES_JSON) ?: emptyList())
    )
    val noticesState: StateFlow<RepoState<List<Notice>>> = _noticesState.asStateFlow()

    private val _facultyState = MutableStateFlow(
        RepoState<List<Faculty>>(data = JsonParser.parseFaculty(DefaultData.FACULTY_JSON) ?: emptyList())
    )
    val facultyState: StateFlow<RepoState<List<Faculty>>> = _facultyState.asStateFlow()

    private val _resultsState = MutableStateFlow(
        RepoState<List<ResultItem>>(data = JsonParser.parseResults(DefaultData.RESULTS_JSON) ?: emptyList())
    )
    val resultsState: StateFlow<RepoState<List<ResultItem>>> = _resultsState.asStateFlow()

    private val _bannersState = MutableStateFlow(
        RepoState<List<BannerItem>>(data = JsonParser.parseBanners(DefaultData.BANNERS_JSON) ?: emptyList())
    )
    val bannersState: StateFlow<RepoState<List<BannerItem>>> = _bannersState.asStateFlow()

    private val _galleryState = MutableStateFlow(
        RepoState<List<GalleryItem>>(data = JsonParser.parseGallery(DefaultData.GALLERY_JSON) ?: emptyList())
    )
    val galleryState: StateFlow<RepoState<List<GalleryItem>>> = _galleryState.asStateFlow()

    private val _settingsState = MutableStateFlow(
        RepoState(data = JsonParser.parseSettings(DefaultData.SETTINGS_JSON) ?: InstituteSettings())
    )
    val settingsState: StateFlow<RepoState<InstituteSettings>> = _settingsState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    private val _globalError = MutableStateFlow<String?>(null)
    val globalError: StateFlow<String?> = _globalError.asStateFlow()

    fun clearGlobalError() {
        _globalError.value = null
    }

    suspend fun setBaseUrl(newUrl: String) {
        val sanitized = newUrl.trim().let { if (it.endsWith("/")) it else "$it/" }
        prefs.edit().putString(KEY_BASE_URL, sanitized).apply()
        _baseUrl.value = sanitized
        refreshAll()
    }

    suspend fun resetBaseUrl() {
        prefs.edit().remove(KEY_BASE_URL).apply()
        _baseUrl.value = AppConfig.DEFAULT_GITHUB_BASE_URL
        refreshAll()
    }

    /**
     * Loads local Room cache immediately for fast launch, then initiates network sync.
     */
    suspend fun initialize() = withContext(Dispatchers.IO) {
        loadFromCache("courses") { json, ts ->
            JsonParser.parseCourses(json)?.let {
                _coursesState.value = RepoState(it, isFromCache = true, lastUpdated = ts)
            }
        }
        loadFromCache("notices") { json, ts ->
            JsonParser.parseNotices(json)?.let {
                _noticesState.value = RepoState(it, isFromCache = true, lastUpdated = ts)
            }
        }
        loadFromCache("faculty") { json, ts ->
            JsonParser.parseFaculty(json)?.let {
                _facultyState.value = RepoState(it, isFromCache = true, lastUpdated = ts)
            }
        }
        loadFromCache("results") { json, ts ->
            JsonParser.parseResults(json)?.let {
                _resultsState.value = RepoState(it, isFromCache = true, lastUpdated = ts)
            }
        }
        loadFromCache("banners") { json, ts ->
            JsonParser.parseBanners(json)?.let {
                _bannersState.value = RepoState(it, isFromCache = true, lastUpdated = ts)
            }
        }
        loadFromCache("gallery") { json, ts ->
            JsonParser.parseGallery(json)?.let {
                _galleryState.value = RepoState(it, isFromCache = true, lastUpdated = ts)
            }
        }
        loadFromCache("settings") { json, ts ->
            JsonParser.parseSettings(json)?.let {
                _settingsState.value = RepoState(it, isFromCache = true, lastUpdated = ts)
            }
        }

        // Trigger remote sync from GitHub
        refreshAll()
    }

    private suspend fun loadFromCache(key: String, onLoaded: (String, Long) -> Unit) {
        try {
            val cached = cacheDao.getCached(key)
            if (cached != null && cached.jsonData.isNotBlank()) {
                onLoaded(cached.jsonData, cached.lastUpdated)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Fetches all 7 JSON files from GitHub concurrently.
     * Updates state flows and caches response in Room.
     */
    suspend fun refreshAll(): Boolean = withContext(Dispatchers.IO) {
        _isRefreshing.value = true
        _globalError.value = null

        val currentBase = _baseUrl.value
        var anySuccess = false
        var anyFailure = false

        coroutineScope {
            val coursesJob = async {
                fetchAndCache(
                    key = "courses",
                    url = AppConfig.getCoursesUrl(currentBase),
                    parser = { JsonParser.parseCourses(it) },
                    updateState = { list, ts ->
                        _coursesState.value = RepoState(list, isFromCache = false, lastUpdated = ts)
                    },
                    currentState = _coursesState
                )
            }

            val noticesJob = async {
                fetchAndCache(
                    key = "notices",
                    url = AppConfig.getNoticesUrl(currentBase),
                    parser = { JsonParser.parseNotices(it) },
                    updateState = { list, ts ->
                        _noticesState.value = RepoState(list, isFromCache = false, lastUpdated = ts)
                    },
                    currentState = _noticesState
                )
            }

            val facultyJob = async {
                fetchAndCache(
                    key = "faculty",
                    url = AppConfig.getFacultyUrl(currentBase),
                    parser = { JsonParser.parseFaculty(it) },
                    updateState = { list, ts ->
                        _facultyState.value = RepoState(list, isFromCache = false, lastUpdated = ts)
                    },
                    currentState = _facultyState
                )
            }

            val resultsJob = async {
                fetchAndCache(
                    key = "results",
                    url = AppConfig.getResultsUrl(currentBase),
                    parser = { JsonParser.parseResults(it) },
                    updateState = { list, ts ->
                        _resultsState.value = RepoState(list, isFromCache = false, lastUpdated = ts)
                    },
                    currentState = _resultsState
                )
            }

            val bannersJob = async {
                fetchAndCache(
                    key = "banners",
                    url = AppConfig.getBannersUrl(currentBase),
                    parser = { JsonParser.parseBanners(it) },
                    updateState = { list, ts ->
                        _bannersState.value = RepoState(list, isFromCache = false, lastUpdated = ts)
                    },
                    currentState = _bannersState
                )
            }

            val galleryJob = async {
                fetchAndCache(
                    key = "gallery",
                    url = AppConfig.getGalleryUrl(currentBase),
                    parser = { JsonParser.parseGallery(it) },
                    updateState = { list, ts ->
                        _galleryState.value = RepoState(list, isFromCache = false, lastUpdated = ts)
                    },
                    currentState = _galleryState
                )
            }

            val settingsJob = async {
                fetchAndCache(
                    key = "settings",
                    url = AppConfig.getSettingsUrl(currentBase),
                    parser = { JsonParser.parseSettings(it) },
                    updateState = { item, ts ->
                        _settingsState.value = RepoState(item, isFromCache = false, lastUpdated = ts)
                    },
                    currentState = _settingsState
                )
            }

            val results = listOf(
                coursesJob.await(),
                noticesJob.await(),
                facultyJob.await(),
                resultsJob.await(),
                bannersJob.await(),
                galleryJob.await(),
                settingsJob.await()
            )

            anySuccess = results.any { it }
            anyFailure = results.any { !it }
        }

        _isRefreshing.value = false
        if (anyFailure && !anySuccess) {
            _globalError.value = "Unable to connect to GitHub. Showing cached content."
        }
        anySuccess
    }

    private suspend fun <T> fetchAndCache(
        key: String,
        url: String,
        parser: (String) -> T?,
        updateState: (T, Long) -> Unit,
        currentState: MutableStateFlow<RepoState<T>>
    ): Boolean {
        val result = NetworkClient.fetchString(url)
        return if (result.isSuccess) {
            val json = result.getOrNull() ?: ""
            val parsed = parser(json)
            if (parsed != null) {
                val now = System.currentTimeMillis()
                cacheDao.insertOrUpdate(CachedDataEntity(key, json, now))
                updateState(parsed, now)
                true
            } else {
                currentState.value = currentState.value.copy(
                    errorMessage = "Failed to parse $key data from GitHub"
                )
                false
            }
        } else {
            val err = result.exceptionOrNull()?.message ?: "Network error"
            currentState.value = currentState.value.copy(
                errorMessage = err
            )
            false
        }
    }

    /**
     * Performs instant global search across Courses, Notices, Faculty, and Results.
     */
    fun search(query: String): SearchResult {
        val q = query.trim().lowercase()
        if (q.isBlank()) return SearchResult()

        val courses = _coursesState.value.data.filter {
            it.title.lowercase().contains(q) ||
            it.category.lowercase().contains(q) ||
            it.description.lowercase().contains(q)
        }

        val notices = _noticesState.value.data.filter {
            it.title.lowercase().contains(q) ||
            it.description.lowercase().contains(q) ||
            it.category.lowercase().contains(q)
        }

        val faculty = _facultyState.value.data.filter {
            it.name.lowercase().contains(q) ||
            it.subject.lowercase().contains(q) ||
            it.qualification.lowercase().contains(q) ||
            it.bio.lowercase().contains(q)
        }

        val results = _resultsState.value.data.filter {
            it.studentName.lowercase().contains(q) ||
            it.exam.lowercase().contains(q) ||
            it.rankResult.lowercase().contains(q) ||
            it.year.lowercase().contains(q)
        }

        return SearchResult(courses, notices, faculty, results)
    }
}
