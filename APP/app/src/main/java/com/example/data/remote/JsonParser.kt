package com.example.data.remote

import com.example.data.model.BannerItem
import com.example.data.model.Course
import com.example.data.model.Faculty
import com.example.data.model.GalleryItem
import com.example.data.model.InstituteSettings
import com.example.data.model.Notice
import com.example.data.model.ResultItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * Moshi-based JSON Parser for all Medha Mantra remote content models.
 */
object JsonParser {

    val moshi: Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val courseListType = Types.newParameterizedType(List::class.java, Course::class.java)
    private val noticeListType = Types.newParameterizedType(List::class.java, Notice::class.java)
    private val facultyListType = Types.newParameterizedType(List::class.java, Faculty::class.java)
    private val resultListType = Types.newParameterizedType(List::class.java, ResultItem::class.java)
    private val bannerListType = Types.newParameterizedType(List::class.java, BannerItem::class.java)
    private val galleryListType = Types.newParameterizedType(List::class.java, GalleryItem::class.java)

    fun parseCourses(json: String): List<Course>? {
        return try {
            val adapter = moshi.adapter<List<Course>>(courseListType)
            adapter.fromJson(json)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun parseNotices(json: String): List<Notice>? {
        return try {
            val adapter = moshi.adapter<List<Notice>>(noticeListType)
            adapter.fromJson(json)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun parseFaculty(json: String): List<Faculty>? {
        return try {
            val adapter = moshi.adapter<List<Faculty>>(facultyListType)
            adapter.fromJson(json)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun parseResults(json: String): List<ResultItem>? {
        return try {
            val adapter = moshi.adapter<List<ResultItem>>(resultListType)
            adapter.fromJson(json)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun parseBanners(json: String): List<BannerItem>? {
        return try {
            val adapter = moshi.adapter<List<BannerItem>>(bannerListType)
            adapter.fromJson(json)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun parseGallery(json: String): List<GalleryItem>? {
        return try {
            val adapter = moshi.adapter<List<GalleryItem>>(galleryListType)
            adapter.fromJson(json)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun parseSettings(json: String): InstituteSettings? {
        return try {
            val adapter = moshi.adapter(InstituteSettings::class.java)
            adapter.fromJson(json)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
