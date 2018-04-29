package com.vlabs.jsonplaceholder.cache.model.todos

import android.database.sqlite.SQLiteDatabase
import com.vlabs.jsonplaceholder.cache.PreferencesHelper
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.DbOpenHelper
import com.vlabs.jsonplaceholder.cache.db.constants.todos.TodoConstants
import com.vlabs.jsonplaceholder.cache.db.mapper.todos.TodoMapper
import com.vlabs.jsonplaceholder.data.mappers.todos.TodoEntityMapper
import com.vlabs.jsonplaceholder.data.models.BufferooEntity
import com.vlabs.jsonplaceholder.data.models.TodoEntity
import com.vlabs.jsonplaceholder.data.repositories.todos.TodoCache
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Cached implementation for retrieving and saving Bufferoo instances. This class implements the
 * [PostCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class TodoCacheImpl @Inject constructor(dbOpenHelper: DbOpenHelper,
                                        private val entityMapper: TodoEntityMapper,
                                        private val mapper: TodoMapper,
                                        private val preferencesHelper: PreferencesHelper):
        TodoCache {

    override fun getTodosByUserId(userId: Int): Single<List<TodoEntity>> {
        return Single.defer<List<TodoEntity>> {
            val updatesCursor = database.rawQuery(String.format(TodoConstants.QUERY_GET_TODOS_BY_USER_ID, userId), null)
            val albums = mutableListOf<TodoEntity>()

            while (updatesCursor.moveToNext()) {
                val cachedUser = mapper.parseCursor(updatesCursor)
                albums.add(entityMapper.mapFromCached(cachedUser))
            }

            updatesCursor.close()
            Single.just<List<TodoEntity>>(albums)
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
    override fun clearTodos(): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                database.delete(Db.TodoTable.TABLE_NAME, null, null)
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
    override fun saveTodos(users: List<TodoEntity>): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                users.forEach {
                    saveTodo(entityMapper.mapToCached(it))
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
    override fun getTodos(): Single<List<TodoEntity>> {
        return Single.defer<List<TodoEntity>> {
            val updatesCursor = database.rawQuery(TodoConstants.QUERY_GET_ALL_TODOS, null)
            val users = mutableListOf<TodoEntity>()

            while (updatesCursor.moveToNext()) {
                val cachedTodo = mapper.parseCursor(updatesCursor)
                users.add(entityMapper.mapFromCached(cachedTodo))
            }

            updatesCursor.close()
            Single.just<List<TodoEntity>>(users)
        }
    }

    /**
     * Helper method for saving a [CachedPost] instance to the database.
     */
    private fun saveTodo(cachedTodo: CachedTodo) {
        database.insert(Db.TodoTable.TABLE_NAME, null, mapper.toContentValues(cachedTodo))
    }

    /**
     * Checked whether there are instances of [CachedPost] stored in the cache
     */
    override fun isCached(userId: Int): Boolean {
        return database.rawQuery(String.format(TodoConstants.QUERY_GET_TODOS_BY_USER_ID, userId), null).count > 0
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