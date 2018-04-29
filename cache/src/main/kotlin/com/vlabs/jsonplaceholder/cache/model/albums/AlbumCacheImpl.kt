package com.vlabs.jsonplaceholder.cache.model.albums

import android.database.sqlite.SQLiteDatabase
import com.vlabs.jsonplaceholder.cache.PreferencesHelper
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.DbOpenHelper
import com.vlabs.jsonplaceholder.cache.db.constants.albums.AlbumConstants
import com.vlabs.jsonplaceholder.cache.db.constants.users.UserConstants
import com.vlabs.jsonplaceholder.cache.db.mapper.albums.AlbumMapper
import com.vlabs.jsonplaceholder.data.mappers.albums.AlbumEntityMapper
import com.vlabs.jsonplaceholder.data.models.AlbumEntity
import com.vlabs.jsonplaceholder.data.models.BufferooEntity
import com.vlabs.jsonplaceholder.data.models.UserEntity
import com.vlabs.jsonplaceholder.data.repositories.albums.AlbumCache
import com.vlabs.jsonplaceholder.data.repositories.users.UserCache
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Cached implementation for retrieving and saving Bufferoo instances. This class implements the
 * [PostCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class AlbumCacheImpl @Inject constructor(dbOpenHelper: DbOpenHelper,
                                         private val entityMapper: AlbumEntityMapper,
                                         private val mapper: AlbumMapper,
                                         private val preferencesHelper: PreferencesHelper):
        AlbumCache {

    override fun getAlbumsByUserId(userId: Int): Single<List<AlbumEntity>> {
        //db.rawQuery("select * from table where column = ?",new String[]{"data"});
        return Single.defer<List<AlbumEntity>> {
            val updatesCursor = database.rawQuery(String.format(AlbumConstants.QUERY_GET_ALBUMS_BY_USER_ID, userId), null)
            val albums = mutableListOf<AlbumEntity>()

            while (updatesCursor.moveToNext()) {
                val cachedUser = mapper.parseCursor(updatesCursor)
                albums.add(entityMapper.mapFromCached(cachedUser))
            }

            updatesCursor.close()
            Single.just<List<AlbumEntity>>(albums)
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
    override fun clearAlbums(): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                database.delete(Db.AlbumTable.TABLE_NAME, null, null)
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
    override fun saveAlbums(users: List<AlbumEntity>): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                users.forEach {
                    saveAlbum(entityMapper.mapToCached(it))
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
    override fun getAlbums(): Single<List<AlbumEntity>> {
        return Single.defer<List<AlbumEntity>> {
            val updatesCursor = database.rawQuery(AlbumConstants.QUERY_GET_ALL_ALBUMS, null)
            val albums = mutableListOf<AlbumEntity>()

            while (updatesCursor.moveToNext()) {
                val cachedUser = mapper.parseCursor(updatesCursor)
                albums.add(entityMapper.mapFromCached(cachedUser))
            }

            updatesCursor.close()
            Single.just<List<AlbumEntity>>(albums)
        }
    }

    /**
     * Helper method for saving a [CachedPost] instance to the database.
     */
    private fun saveAlbum(cachedUser: CachedAlbum) {
        database.insert(Db.AlbumTable.TABLE_NAME, null, mapper.toContentValues(cachedUser))
    }

    /**
     * Checked whether there are instances of [CachedPost] stored in the cache
     */
    override fun isCached(userId: Int): Boolean {
        return database.rawQuery(String.format(AlbumConstants.QUERY_GET_ALBUMS_BY_USER_ID, userId), null).count > 0
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