package com.vlabs.jsonplaceholder.cache.db.mapper

import android.database.Cursor
import com.gironnet.damien.cache.BuildConfig
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.DbOpenHelper
import com.vlabs.jsonplaceholder.cache.db.mapper.photos.PhotoMapper
import com.vlabs.jsonplaceholder.cache.model.photos.CachedPhoto
import com.vlabs.jsonplaceholder.cache.test.DefaultConfig
import com.vlabs.jsonplaceholder.cache.test.factory.PhotoFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(DefaultConfig.EMULATE_SDK))
class PhotoMapperTest {

    private lateinit var photoMapper: PhotoMapper
    private val database = DbOpenHelper(RuntimeEnvironment.application).writableDatabase

    @Before
    fun setUp() {
        photoMapper = PhotoMapper()
    }

    @Test
    fun parseCursorMapsData() {
        val cachedPhoto = PhotoFactory.makeCachedPhoto()
        insertCachedPhoto(cachedPhoto)

        val cursor = retrieveCachedPhotoCursor()
        assertEquals(cachedPhoto, photoMapper.parseCursor(cursor))
    }

    private fun retrieveCachedPhotoCursor(): Cursor {
        val cursor = database.rawQuery("SELECT * FROM " + Db.PhotoTable.TABLE_NAME, null)
        cursor.moveToFirst()
        return cursor
    }

    private fun insertCachedPhoto(cachedPhoto: CachedPhoto) {
        database.insertOrThrow(Db.PhotoTable.TABLE_NAME, null,
                photoMapper.toContentValues(cachedPhoto))
    }

}