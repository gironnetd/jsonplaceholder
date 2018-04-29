package com.vlabs.jsonplaceholder.cache.cache

import com.vlabs.jsonplaceholder.cache.PreferencesHelper
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.DbOpenHelper
import com.vlabs.jsonplaceholder.cache.db.mapper.users.UserMapper
import com.vlabs.jsonplaceholder.cache.mapper.users.UserEntityMapper
import com.vlabs.jsonplaceholder.cache.model.users.CachedUser
import com.vlabs.jsonplaceholder.cache.model.users.UserCacheImpl
import com.vlabs.jsonplaceholder.cache.test.factory.UserFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21))
class UserCacheImplTest {

    private var entityMapper = UserEntityMapper()
    private var mapper = UserMapper()
    private var preferencesHelper = PreferencesHelper(RuntimeEnvironment.application)

    private val databaseHelper = UserCacheImpl(DbOpenHelper(RuntimeEnvironment.application),
            entityMapper, mapper, preferencesHelper)

    @Before
    fun setup() {
        databaseHelper.getDatabase().rawQuery("DELETE FROM " + Db.UserTable.TABLE_NAME, null)
    }

    @Test
    fun clearTablesCompletes() {
        val testObserver = databaseHelper.clearUsers().test()
        testObserver.assertComplete()
    }

    //<editor-fold desc="Save Users">
    @Test
    fun saveUsersCompletes() {
        val userEntities = UserFactory.makeUserEntityList(2)

        val testObserver = databaseHelper.saveUsers(userEntities).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveUsersSavesData() {
        val userCount = 2
        val userEntities = UserFactory.makeUserEntityList(userCount)

        databaseHelper.saveUsers(userEntities).test()
        checkNumRowsInUsersTable(userCount)
    }
    //</editor-fold>

    //<editor-fold desc="Get Users">
    @Test
    fun getUsersCompletes() {
        val testObserver = databaseHelper.getUsers().test()
        testObserver.assertComplete()
    }

    @Test
    fun getUsersReturnsData() {
        val userEntities = UserFactory.makeUserEntityList(2)
        val cachedUsers = mutableListOf<CachedUser>()
        userEntities.forEach {
            cachedUsers.add(entityMapper.mapToCached(it))
        }
        insertUsers(cachedUsers)

        val testObserver = databaseHelper.getUsers().test()
        testObserver.assertValue(userEntities)
    }
    //</editor-fold>

    private fun insertUsers(cachedUsers: List<CachedUser>) {
        val database = databaseHelper.getDatabase()
        cachedUsers.forEach {
            database.insertOrThrow(Db.UserTable.TABLE_NAME, null,
                    mapper.toContentValues(it))
        }
    }

    private fun checkNumRowsInUsersTable(expectedRows: Int) {
        val usersCursor = databaseHelper.getDatabase().query(Db.UserTable.TABLE_NAME,
                null, null, null, null, null, null)
        usersCursor.moveToFirst()
        val numberOfRows = usersCursor.count
        usersCursor.close()
        assertEquals(expectedRows, numberOfRows)
    }

}