package com.vlabs.jsonplaceholder.cache.db.mapper

import android.database.Cursor
import com.gironnet.damien.cache.BuildConfig
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.DbOpenHelper
import com.vlabs.jsonplaceholder.cache.db.mapper.users.UserMapper
import com.vlabs.jsonplaceholder.cache.model.users.CachedUser
import com.vlabs.jsonplaceholder.cache.test.DefaultConfig
import com.vlabs.jsonplaceholder.cache.test.factory.UserFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(DefaultConfig.EMULATE_SDK))
class UserMapperTest {

    private lateinit var userMapper: UserMapper
    private val database = DbOpenHelper(RuntimeEnvironment.application).writableDatabase

    @Before
    fun setUp() {
        userMapper = UserMapper()
    }

    @Test
    fun parseCursorMapsData() {
        val cachedUser = UserFactory.makeCachedUser()
        insertCachedUser(cachedUser)

        val cursor = retrieveCachedUserCursor()
        assertEquals(cachedUser, userMapper.parseCursor(cursor))
    }

    private fun retrieveCachedUserCursor(): Cursor {
        val cursor = database.rawQuery("SELECT * FROM " + Db.UserTable.TABLE_NAME, null)
        cursor.moveToFirst()
        return cursor
    }

    private fun insertCachedUser(cachedUser: CachedUser) {
        database.insertOrThrow(Db.UserTable.TABLE_NAME, null,
                userMapper.toContentValues(cachedUser))
    }

}