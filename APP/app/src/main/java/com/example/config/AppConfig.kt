package com.example.config

/**
 * =========================================================================================
 * MEDHA MANTRA Competitive Institute - Central Configuration
 * =========================================================================================
 *
 * CONTENT UPDATE BEHAVIOR (CRITICAL ARCHITECTURAL NOTE):
 * -----------------------------------------------------------------------------------------
 * 1. DYNAMIC CONTENT UPDATES (NO APK REBUILD NEEDED):
 *    - All Course offerings, Notices, Faculty profiles, Student results, Homepage banners,
 *      Gallery photos, and Institute settings (phone, email, address, social links) are loaded
 *      dynamically from GitHub at runtime.
 *    - To change or add content, simply edit the corresponding JSON file in your GitHub repository
 *      or upload new images/PDF documents to their respective URLs.
 *    - The Android app fetches latest JSON files immediately upon startup or when users
 *      pull-down to refresh.
 *    - **NO NEW APK OR AAB BUILD IS REQUIRED** when updating banners, courses, notices,
 *      faculty, results, gallery, or contact settings in GitHub.
 *
 * 2. APP CODE / FUNCTIONALITY CHANGES (APK REBUILD REQUIRED):
 *    - Only code modifications (e.g. adding new UI screens, changing Android manifest permissions,
 *      altering native Compose components, or releasing new major app features) require
 *      generating a new APK or AAB release.
 * =========================================================================================
 */
object AppConfig {

    /**
     * CENTRAL GITHUB BASE URL:
     * Point this to your GitHub Pages URL or Raw GitHub content URL.
     *
     * Format 1 (GitHub Pages):
     * "https://YOUR-GITHUB-USERNAME.github.io/YOUR-REPOSITORY/"
     *
     * Format 2 (GitHub Raw Content):
     * "https://raw.githubusercontent.com/YOUR-GITHUB-USERNAME/YOUR-REPOSITORY/main/"
     *
     * Default configured repository for MEDHA MANTRA:
     */
    const val DEFAULT_GITHUB_BASE_URL: String =
        "https://raw.githubusercontent.com/medhamantra/institute-content/main/"

    // Relative endpoint paths matching Requirement 5 & 23
    const val PATH_COURSES = "data/courses.json"
    const val PATH_NOTICES = "data/notices.json"
    const val PATH_FACULTY = "data/faculty.json"
    const val PATH_RESULTS = "data/results.json"
    const val PATH_BANNERS = "data/banners.json"
    const val PATH_GALLERY = "data/gallery.json"
    const val PATH_SETTINGS = "data/settings.json"

    // Helper functions constructing full remote URLs from base URL
    fun getCoursesUrl(baseUrl: String = DEFAULT_GITHUB_BASE_URL): String =
        sanitizeBaseUrl(baseUrl) + PATH_COURSES

    fun getNoticesUrl(baseUrl: String = DEFAULT_GITHUB_BASE_URL): String =
        sanitizeBaseUrl(baseUrl) + PATH_NOTICES

    fun getFacultyUrl(baseUrl: String = DEFAULT_GITHUB_BASE_URL): String =
        sanitizeBaseUrl(baseUrl) + PATH_FACULTY

    fun getResultsUrl(baseUrl: String = DEFAULT_GITHUB_BASE_URL): String =
        sanitizeBaseUrl(baseUrl) + PATH_RESULTS

    fun getBannersUrl(baseUrl: String = DEFAULT_GITHUB_BASE_URL): String =
        sanitizeBaseUrl(baseUrl) + PATH_BANNERS

    fun getGalleryUrl(baseUrl: String = DEFAULT_GITHUB_BASE_URL): String =
        sanitizeBaseUrl(baseUrl) + PATH_GALLERY

    fun getSettingsUrl(baseUrl: String = DEFAULT_GITHUB_BASE_URL): String =
        sanitizeBaseUrl(baseUrl) + PATH_SETTINGS

    private fun sanitizeBaseUrl(url: String): String {
        val trimmed = url.trim()
        return if (trimmed.endsWith("/")) trimmed else "$trimmed/"
    }

    // Default static fallback constants for institute identity
    const val INSTITUTE_NAME = "MEDHA MANTRA Competitive Institute"
    const val INSTITUTE_TAGLINE = "EMPOWERING MINDS, CREATING FUTURES"
    const val DEFAULT_PHONE = "6297034796"
    const val DEFAULT_WHATSAPP = "6297034796"
    const val DEFAULT_EMAIL = "medhamantra.memari@gmail.com"
    const val DEFAULT_WEBSITE = "https://medhamantra.com"
    const val DEFAULT_ADDRESS =
        "1st Floor, Malancha Complex, Chakdighi Road, Near Abhijan Sangha Club, Memari, Purba Bardhaman 713146"
    const val DEFAULT_MAPS_URL =
        "https://maps.google.com/?q=Malancha+Complex+Chakdighi+Road+Memari+Purba+Bardhaman+713146"

    // Banner slider auto-advance interval in milliseconds (Requirement 4: every 5 seconds)
    const val BANNER_SLIDE_INTERVAL_MS = 5000L
}
