package com.vlabs.jsonplaceholder.cache.model.comments

import android.database.sqlite.SQLiteDatabase
import com.vlabs.jsonplaceholder.cache.PreferencesHelper
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.DbOpenHelper
import com.vlabs.jsonplaceholder.cache.db.constants.comments.CommentConstants
import com.vlabs.jsonplaceholder.cache.db.constants.users.UserConstants
import com.vlabs.jsonplaceholder.cache.db.mapper.comments.CommentMapper
import com.vlabs.jsonplaceholder.cache.db.mapper.users.UserMapper
import com.vlabs.jsonplaceholder.cache.mapper.users.UserEntityMapper
import com.vlabs.jsonplaceholder.data.mappers.comments.CommentEntityMapper
import com.vlabs.jsonplaceholder.data.models.BufferooEntity
import com.vlabs.jsonplaceholder.data.models.CommentEntity
import com.vlabs.jsonplaceholder.data.models.UserEntity
import com.vlabs.jsonplaceholder.data.repositories.comments.CommentCache
import com.vlabs.jsonplaceholder.data.repositories.users.UserCache
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Cached implementation for retrieving and saving Bufferoo instances. This class implements the
 * [PostCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class CommentCacheImpl @Inject constructor(dbOpenHelper: DbOpenHelper,
                                           private val entityMapper: CommentEntityMapper,
                                           private val mapper: CommentMapper,
                                           private val preferencesHelper: PreferencesHelper):
        CommentCache {

    override fun getCommentsByPostId(postId: Int): Single<List<CommentEntity>> {
        return Single.defer<List<CommentEntity>> {
            val updatesCursor = database.rawQuery(String.format(CommentConstants.QUERY_GET_COMMENTS_BY_POST_ID, postId), null)
            val comments = mutableListOf<CommentEntity>()

            while (updatesCursor.moveToNext()) {
                val cachedUser = mapper.parseCursor(updatesCursor)
                comments.add(entityMapper.mapFromCached(cachedUser))
            }

            updatesCursor.close()
            Single.just<List<CommentEntity>>(comments)
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
    override fun clearComments(): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                database.delete(Db.UserTable.TABLE_NAME, null, null)
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
    override fun saveComments(users: List<CommentEntity>): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                users.forEach {
                    saveComment(entityMapper.mapToCached(it))
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
    override fun getComments(): Single<List<CommentEntity>> {
        return Single.defer<List<CommentEntity>> {
            val updatesCursor = database.rawQuery(CommentConstants.QUERY_GET_ALL_COMMENTS, null)
            val comments = mutableListOf<CommentEntity>()

            while (updatesCursor.moveToNext()) {
                val cachedUser = mapper.parseCursor(updatesCursor)
                comments.add(entityMapper.mapFromCached(cachedUser))
            }

            updatesCursor.close()
            Single.just<List<CommentEntity>>(comments)
        }
    }

    /**
     * Helper method for saving a [CachedPost] instance to the database.
     */
    private fun saveComment(cachedUser: CachedComment) {
        database.insert(Db.CommentTable.TABLE_NAME, null, mapper.toContentValues(cachedUser))
    }

    /**
     * Checked whether there are instances of [CachedPost] stored in the cache
     */
    override fun isCached(postId: Int): Boolean {
        return database.rawQuery(String.format(CommentConstants.QUERY_GET_COMMENTS_BY_POST_ID, postId), null).count > 0
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