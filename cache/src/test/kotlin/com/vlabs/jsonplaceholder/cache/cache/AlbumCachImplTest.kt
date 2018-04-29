package com.vlabs.jsonplaceholder.cache.cache

import com.vlabs.jsonplaceholder.cache.PreferencesHelper
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.DbOpenHelper
import com.vlabs.jsonplaceholder.cache.db.mapper.albums.AlbumMapper
import com.vlabs.jsonplaceholder.cache.model.albums.AlbumCacheImpl
import com.vlabs.jsonplaceholder.cache.model.albums.CachedAlbum
import com.vlabs.jsonplaceholder.cache.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.data.mappers.albums.AlbumEntityMapper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21))
class AlbumCachImplTest {

    private var entityMapper = AlbumEntityMapper()
    private var mapper = AlbumMapper()
    private var preferencesHelper = PreferencesHelper(RuntimeEnvironment.application)

    private val databaseHelper = AlbumCacheImpl(DbOpenHelper(RuntimeEnvironment.application),
            entityMapper, mapper, preferencesHelper)

    @Before
    fun setup() {
        databaseHelper.getDatabase().rawQuery("DELETE FROM " + Db.AlbumTable.TABLE_NAME, null)
    }

    @Test
    fun clearTablesCompletes() {
        val testObserver = databaseHelper.clearAlbums().test()
        testObserver.assertComplete()
    }

    //<editor-fold desc="Save Albums">
    @Test
    fun saveAlbumsCompletes() {
        val albumEntities = AlbumFactory.makeAlbumEntityList(2)

        val testObserver = databaseHelper.saveAlbums(albumEntities).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveAlbumsSavesData() {
        val albumCount = 2
        val albumEntities = AlbumFactory.makeAlbumEntityList(albumCount)

        databaseHelper.saveAlbums(albumEntities).test()
        checkNumRowsInAlbumsTable(albumCount)
    }
    //</editor-fold>

    //<editor-fold desc="Get Albums">
    @Test
    fun getAlbumsCompletes() {
        val testObserver = databaseHelper.getAlbums().test()
        testObserver.assertComplete()
    }

    @Test
    fun getAlbumsReturnsData() {
        val albumEntities = AlbumFactory.makeAlbumEntityList(2)
        val cachedAlbums = mutableListOf<CachedAlbum>()
        albumEntities.forEach {
            cachedAlbums.add(entityMapper.mapToCached(it))
        }
        insertAlbums(cachedAlbums)

        val testObserver = databaseHelper.getAlbums().test()
        testObserver.assertValue(albumEntities)
    }
    //</editor-fold>

    private fun insertAlbums(cachedAlbums: List<CachedAlbum>) {
        val database = databaseHelper.getDatabase()
        cachedAlbums.forEach {
            database.insertOrThrow(Db.AlbumTable.TABLE_NAME, null,
                    mapper.toContentValues(it))
        }
    }

    private fun checkNumRowsInAlbumsTable(expectedRows: Int) {
        val albumsCursor = databaseHelper.getDatabase().query(Db.AlbumTable.TABLE_NAME,
                null, null, null, null, null, null)
        albumsCursor.moveToFirst()
        val numberOfRows = albumsCursor.count
        albumsCursor.close()
        assertEquals(expectedRows, numberOfRows)
    }

}