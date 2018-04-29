package com.vlabs.jsonplaceholder.cache.db.mapper

import android.database.Cursor
import com.gironnet.damien.cache.BuildConfig
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.DbOpenHelper
import com.vlabs.jsonplaceholder.cache.db.mapper.todos.TodoMapper
import com.vlabs.jsonplaceholder.cache.model.todos.CachedTodo
import com.vlabs.jsonplaceholder.cache.test.DefaultConfig
import com.vlabs.jsonplaceholder.cache.test.factory.TodoFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(DefaultConfig.EMULATE_SDK))
class TodoMapperTest {

    private lateinit var todoMapper: TodoMapper
    private val database = DbOpenHelper(RuntimeEnvironment.application).writableDatabase

    @Before
    fun setUp() {
        todoMapper = TodoMapper()
    }

    @Test
    fun parseCursorMapsData() {
        val cachedTodo = TodoFactory.makeCachedTodo()
        insertCachedTodo(cachedTodo)

        val cursor = retrieveCachedTodoCursor()
        assertEquals(cachedTodo, todoMapper.parseCursor(cursor))
    }

    private fun retrieveCachedTodoCursor(): Cursor {
        val cursor = database.rawQuery("SELECT * FROM " + Db.TodoTable.TABLE_NAME, null)
        cursor.moveToFirst()
        return cursor
    }

    private fun insertCachedTodo(cachedTodo: CachedTodo) {
        database.insertOrThrow(Db.TodoTable.TABLE_NAME, null,
                todoMapper.toContentValues(cachedTodo))
    }

}