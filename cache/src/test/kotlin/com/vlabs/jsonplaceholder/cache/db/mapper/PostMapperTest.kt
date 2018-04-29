package com.vlabs.jsonplaceholder.cache.db.mapper

import android.database.Cursor
import com.gironnet.damien.cache.BuildConfig
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.DbOpenHelper
import com.vlabs.jsonplaceholder.cache.db.mapper.posts.PostMapper
import com.vlabs.jsonplaceholder.cache.model.users.CachedPost
import com.vlabs.jsonplaceholder.cache.test.DefaultConfig
import com.vlabs.jsonplaceholder.cache.test.factory.PostFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(DefaultConfig.EMULATE_SDK))
class PostMapperTest {

    private lateinit var postMapper: PostMapper
    private val database = DbOpenHelper(RuntimeEnvironment.application).writableDatabase

    @Before
    fun setUp() {
        postMapper = PostMapper()
    }

    @Test
    fun parseCursorMapsData() {
        val cachedPost = PostFactory.makeCachedPost()
        insertCachedPost(cachedPost)

        val cursor = retrieveCachedPostCursor()
        assertEquals(cachedPost, postMapper.parseCursor(cursor))
    }

    private fun retrieveCachedPostCursor(): Cursor {
        val cursor = database.rawQuery("SELECT * FROM " + Db.PostTable.TABLE_NAME, null)
        cursor.moveToFirst()
        return cursor
    }

    private fun insertCachedPost(cachedPost: CachedPost) {
        database.insertOrThrow(Db.PostTable.TABLE_NAME, null,
                postMapper.toContentValues(cachedPost))
    }

}