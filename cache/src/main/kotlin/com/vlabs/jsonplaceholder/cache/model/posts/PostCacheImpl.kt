package com.vlabs.jsonplaceholder.cache.model.users

import android.database.sqlite.SQLiteDatabase
import com.vlabs.jsonplaceholder.cache.PreferencesHelper
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.DbOpenHelper
import com.vlabs.jsonplaceholder.cache.db.constants.posts.PostConstants
import com.vlabs.jsonplaceholder.cache.db.mapper.posts.PostMapper
import com.vlabs.jsonplaceholder.cache.mapper.posts.PostEntityMapper
import com.vlabs.jsonplaceholder.data.models.BufferooEntity
import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.data.repositories.posts.PostCache
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Cached implementation for retrieving and saving Bufferoo instances. This class implements the
 * [AlbumCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class PostCacheImpl @Inject constructor(dbOpenHelper: DbOpenHelper,
                                        private val entityMapper: PostEntityMapper,
                                        private val mapper: PostMapper,
                                        private val preferencesHelper: PreferencesHelper):
        PostCache {

    override fun getPostsByUserId(userId: Int): Single<List<PostEntity>> {

        return Single.defer<List<PostEntity>> {
            val updatesCursor = database.rawQuery(String.format(PostConstants.QUERY_GET_POSTS_BY_USER_ID, userId), null)
            val albums = mutableListOf<PostEntity>()

            while (updatesCursor.moveToNext()) {
                val cachedUser = mapper.parseCursor(updatesCursor)
                albums.add(entityMapper.mapFromCached(cachedUser))
            }

            updatesCursor.close()
            Single.just<List<PostEntity>>(albums)
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
    override fun clearPosts(): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                database.delete(Db.PostTable.TABLE_NAME, null, null)
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
    override fun savePosts(posts: List<PostEntity>): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                posts.forEach {
                    saveUser(entityMapper.mapToCached(it))
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
    override fun getPosts(): Single<List<PostEntity>> {
        return Single.defer<List<PostEntity>> {
            val updatesCursor = database.rawQuery(PostConstants.QUERY_GET_ALL_POSTS, null)
            val posts = mutableListOf<PostEntity>()

            while (updatesCursor.moveToNext()) {
                val cachedPost = mapper.parseCursor(updatesCursor)
                posts.add(entityMapper.mapFromCached(cachedPost))
            }

            updatesCursor.close()
            Single.just<List<PostEntity>>(posts)
        }
    }

    /**
     * Helper method for saving a [CachedPost] instance to the database.
     */
    private fun saveUser(cachedPost: CachedPost) {
        database.insert(Db.PostTable.TABLE_NAME, null, mapper.toContentValues(cachedPost))
    }

    /**
     * Checked whether there are instances of [CachedPost] stored in the cache
     */
    override fun isCached(userId: Int): Boolean {
        return database.rawQuery(String.format(PostConstants.QUERY_GET_POSTS_BY_USER_ID, userId), null).count > 0
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