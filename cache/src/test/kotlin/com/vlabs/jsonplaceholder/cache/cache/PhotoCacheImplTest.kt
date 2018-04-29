package com.vlabs.jsonplaceholder.cache.cache

import com.vlabs.jsonplaceholder.cache.PreferencesHelper
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.DbOpenHelper
import com.vlabs.jsonplaceholder.cache.db.mapper.photos.PhotoMapper
import com.vlabs.jsonplaceholder.cache.model.photos.CachedPhoto
import com.vlabs.jsonplaceholder.cache.model.photos.PhotoCacheImpl
import com.vlabs.jsonplaceholder.cache.test.factory.PhotoFactory
import com.vlabs.jsonplaceholder.data.mappers.photos.PhotoEntityMapper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21))
class PhotoCacheImplTest {

    private var entityMapper = PhotoEntityMapper()
    private var mapper = PhotoMapper()
    private var preferencesHelper = PreferencesHelper(RuntimeEnvironment.application)

    private val databaseHelper = PhotoCacheImpl(DbOpenHelper(RuntimeEnvironment.application),
            entityMapper, mapper, preferencesHelper)

    @Before
    fun setup() {
        databaseHelper.getDatabase().rawQuery("DELETE FROM " + Db.PhotoTable.TABLE_NAME, null)
    }

    @Test
    fun clearTablesCompletes() {
        val testObserver = databaseHelper.clearPhotos().test()
        testObserver.assertComplete()
    }

    //<editor-fold desc="Save Photos">
    @Test
    fun savePhotosCompletes() {
        val photoEntities = PhotoFactory.makePhotoEntityList(2)

        val testObserver = databaseHelper.savePhotos(photoEntities).test()
        testObserver.assertComplete()
    }

    @Test
    fun savePhotosSavesData() {
        val photoCount = 2
        val photoEntities = PhotoFactory.makePhotoEntityList(photoCount)

        databaseHelper.savePhotos(photoEntities).test()
        checkNumRowsInPhotosTable(photoCount)
    }
    //</editor-fold>

    //<editor-fold desc="Get Photos">
    @Test
    fun getPhotosCompletes() {
        val testObserver = databaseHelper.getPhotos().test()
        testObserver.assertComplete()
    }

    @Test
    fun getPhotosReturnsData() {
        val photoEntities = PhotoFactory.makePhotoEntityList(2)
        val cachedPhotos = mutableListOf<CachedPhoto>()
        photoEntities.forEach {
            cachedPhotos.add(entityMapper.mapToCached(it))
        }
        insertPhotos(cachedPhotos)

        val testObserver = databaseHelper.getPhotos().test()
        testObserver.assertValue(photoEntities)
    }
    //</editor-fold>

    private fun insertPhotos(cachedPhotos: List<CachedPhoto>) {
        val database = databaseHelper.getDatabase()
        cachedPhotos.forEach {
            database.insertOrThrow(Db.PhotoTable.TABLE_NAME, null,
                    mapper.toContentValues(it))
        }
    }

    private fun checkNumRowsInPhotosTable(expectedRows: Int) {
        val photosCursor = databaseHelper.getDatabase().query(Db.PhotoTable.TABLE_NAME,
                null, null, null, null, null, null)
        photosCursor.moveToFirst()
        val numberOfRows = photosCursor.count
        photosCursor.close()
        assertEquals(expectedRows, numberOfRows)
    }

}