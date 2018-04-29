package com.vlabs.jsonplaceholder.cache.model.photos

import android.database.sqlite.SQLiteDatabase
import com.vlabs.jsonplaceholder.cache.PreferencesHelper
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.DbOpenHelper
import com.vlabs.jsonplaceholder.cache.db.constants.photos.PhotoConstants
import com.vlabs.jsonplaceholder.cache.db.mapper.photos.PhotoMapper
import com.vlabs.jsonplaceholder.data.mappers.photos.PhotoEntityMapper
import com.vlabs.jsonplaceholder.data.models.BufferooEntity
import com.vlabs.jsonplaceholder.data.models.PhotoEntity
import com.vlabs.jsonplaceholder.data.repositories.photos.PhotoCache
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Cached implementation for retrieving and saving Bufferoo instances. This class implements the
 * [PostCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class PhotoCacheImpl @Inject constructor(dbOpenHelper: DbOpenHelper,
                                         private val entityMapper: PhotoEntityMapper,
                                         private val mapper: PhotoMapper,
                                         private val preferencesHelper: PreferencesHelper):
        PhotoCache {

    override fun getPhotosByAlbumId(albumId: Int): Single<List<PhotoEntity>> {
        return Single.defer<List<PhotoEntity>> {
            val updatesCursor = database.rawQuery(String.format(PhotoConstants.QUERY_GET_PHOTOS_BY_ALBUM_ID, albumId), null)
            val photos = mutableListOf<PhotoEntity>()

            while (updatesCursor.moveToNext()) {
                val cachedUser = mapper.parseCursor(updatesCursor)
                photos.add(entityMapper.mapFromCached(cachedUser))
            }

            updatesCursor.close()
            Single.just<List<PhotoEntity>>(photos)
        }
    }

    private val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

    private var database: SQLiteDatabase = dbOpenHelper.writableDatabase

    /**
     * Retrieve an instance from the database, used for tests
     */
    internal fun getDatabase(): SQLiteDatabase {
        return database
    }

    /**
     * Remove all the data from all the tables in the database.
     */
    override fun clearPhotos(): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                database.delete(Db.PhotoTable.TABLE_NAME, null, null)
                database.setTransactionSuccessful()
            } finally {
                database.endTransaction()
            }
            Completable.complete()
        }
    }

    /**
     * Save the given list of [BufferooEntity] instances to the database.
     */
    override fun savePhotos(users: List<PhotoEntity>): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                users.forEach {
                    savePhoto(entityMapper.mapToCached(it))
                }
                database.setTransactionSuccessful()
            } finally {
                database.endTransaction()
            }
            Completable.complete()
        }
    }

    /**
     * Retrieve a list of [BufferooEntity] instances from the database.
     */
    override fun getPhotos(): Single<List<PhotoEntity>> {
        return Single.defer<List<PhotoEntity>> {
            val updatesCursor = database.rawQuery(PhotoConstants.QUERY_GET_ALL_PHOTOS, null)
            val comments = mutableListOf<PhotoEntity>()

            while (updatesCursor.moveToNext()) {
                val cachedPhoto = mapper.parseCursor(updatesCursor)
                comments.add(entityMapper.mapFromCached(cachedPhoto))
            }

            updatesCursor.close()
            Single.just<List<PhotoEntity>>(comments)
        }
    }

    /**
     * Helper method for saving a [CachedPost] instance to the database.
     */
    private fun savePhoto(cachedPhoto: CachedPhoto) {
        database.insert(Db.PhotoTable.TABLE_NAME, null, mapper.toContentValues(cachedPhoto))
    }

    /**
     * Checked whether there are instances of [CachedPost] stored in the cache
     */
    override fun isCached(albumId: Int): Boolean {
        return database.rawQuery(String.format(PhotoConstants.QUERY_GET_PHOTOS_BY_ALBUM_ID, albumId), null).count > 0
    }

    /**
     * Set a point in time at when the cache was last updated
     */
    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    /**
     * Check whether the current cached data exceeds the defined [EXPIRATION_TIME] time
     */
    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }

}