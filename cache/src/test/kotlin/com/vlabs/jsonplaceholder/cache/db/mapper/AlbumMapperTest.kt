package com.vlabs.jsonplaceholder.cache.db.mapper

import android.database.Cursor
import com.gironnet.damien.cache.BuildConfig
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.DbOpenHelper
import com.vlabs.jsonplaceholder.cache.db.mapper.albums.AlbumMapper
import com.vlabs.jsonplaceholder.cache.model.albums.CachedAlbum
import com.vlabs.jsonplaceholder.cache.test.DefaultConfig
import com.vlabs.jsonplaceholder.cache.test.factory.AlbumFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(DefaultConfig.EMULATE_SDK))
class AlbumMapperTest {

    private lateinit var albumMapper: AlbumMapper
    private val database = DbOpenHelper(RuntimeEnvironment.application).writableDatabase

    @Before
    fun setUp() {
        albumMapper = AlbumMapper()
    }

    @Test
    fun parseCursorMapsData() {
        val cachedAlbum = AlbumFactory.makeCachedAlbum()
        insertCachedAlbum(cachedAlbum)

        val cursor = retrieveCachedAlbumCursor()
        assertEquals(cachedAlbum, albumMapper.parseCursor(cursor))
    }

    private fun retrieveCachedAlbumCursor(): Cursor {
        val cursor = database.rawQuery("SELECT * FROM " + Db.AlbumTable.TABLE_NAME, null)
        cursor.moveToFirst()
        return cursor
    }

    private fun insertCachedAlbum(cachedAlbum: CachedAlbum) {
        database.insertOrThrow(Db.AlbumTable.TABLE_NAME, null,
                albumMapper.toContentValues(cachedAlbum))
    }

}