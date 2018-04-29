package com.vlabs.jsonplaceholder.cache.cache

import com.vlabs.jsonplaceholder.cache.PreferencesHelper
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.DbOpenHelper
import com.vlabs.jsonplaceholder.cache.db.mapper.todos.TodoMapper
import com.vlabs.jsonplaceholder.cache.model.todos.CachedTodo
import com.vlabs.jsonplaceholder.cache.model.todos.TodoCacheImpl
import com.vlabs.jsonplaceholder.cache.test.factory.TodoFactory
import com.vlabs.jsonplaceholder.data.mappers.todos.TodoEntityMapper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21))
class TodoCacheImplTest {

    private var entityMapper = TodoEntityMapper()
    private var mapper = TodoMapper()
    private var preferencesHelper = PreferencesHelper(RuntimeEnvironment.application)

    private val databaseHelper = TodoCacheImpl(DbOpenHelper(RuntimeEnvironment.application),
            entityMapper, mapper, preferencesHelper)

    @Before
    fun setup() {
        databaseHelper.getDatabase().rawQuery("DELETE FROM " + Db.TodoTable.TABLE_NAME, null)
    }

    @Test
    fun clearTablesCompletes() {
        val testObserver = databaseHelper.clearTodos().test()
        testObserver.assertComplete()
    }

    //<editor-fold desc="Save Todos">
    @Test
    fun saveTodosCompletes() {
        val todoEntities = TodoFactory.makeTodoEntityList(2)

        val testObserver = databaseHelper.saveTodos(todoEntities).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveTodosSavesData() {
        val todoCount = 2
        val todoEntities = TodoFactory.makeTodoEntityList(todoCount)

        databaseHelper.saveTodos(todoEntities).test()
        checkNumRowsInTodosTable(todoCount)
    }
    //</editor-fold>

    //<editor-fold desc="Get Todos">
    @Test
    fun getTodosCompletes() {
        val testObserver = databaseHelper.getTodos().test()
        testObserver.assertComplete()
    }

    @Test
    fun getTodosReturnsData() {
        val todoEntities = TodoFactory.makeTodoEntityList(2)
        val cachedTodos = mutableListOf<CachedTodo>()
        todoEntities.forEach {
            cachedTodos.add(entityMapper.mapToCached(it))
        }
        insertTodos(cachedTodos)

        val testObserver = databaseHelper.getTodos().test()
        testObserver.assertValue(todoEntities)
    }
    //</editor-fold>

    private fun insertTodos(cachedTodos: List<CachedTodo>) {
        val database = databaseHelper.getDatabase()
        cachedTodos.forEach {
            database.insertOrThrow(Db.TodoTable.TABLE_NAME, null,
                    mapper.toContentValues(it))
        }
    }

    private fun checkNumRowsInTodosTable(expectedRows: Int) {
        val todosCursor = databaseHelper.getDatabase().query(Db.TodoTable.TABLE_NAME,
                null, null, null, null, null, null)
        todosCursor.moveToFirst()
        val numberOfRows = todosCursor.count
        todosCursor.close()
        assertEquals(expectedRows, numberOfRows)
    }

}