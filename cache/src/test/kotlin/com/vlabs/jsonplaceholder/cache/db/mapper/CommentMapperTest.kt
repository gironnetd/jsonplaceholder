package com.vlabs.jsonplaceholder.cache.db.mapper

import android.database.Cursor
import com.gironnet.damien.cache.BuildConfig
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.DbOpenHelper
import com.vlabs.jsonplaceholder.cache.db.mapper.comments.CommentMapper
import com.vlabs.jsonplaceholder.cache.model.comments.CachedComment
import com.vlabs.jsonplaceholder.cache.test.DefaultConfig
import com.vlabs.jsonplaceholder.cache.test.factory.CommentFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(DefaultConfig.EMULATE_SDK))
class CommentMapperTest {

    private lateinit var commentMapper: CommentMapper
    private val database = DbOpenHelper(RuntimeEnvironment.application).writableDatabase

    @Before
    fun setUp() {
        commentMapper = CommentMapper()
    }

    @Test
    fun parseCursorMapsData() {
        val cachedComment = CommentFactory.makeCachedComment()
        insertCachedComment(cachedComment)

        val cursor = retrieveCachedCommentCursor()
        assertEquals(cachedComment, commentMapper.parseCursor(cursor))
    }

    private fun retrieveCachedCommentCursor(): Cursor {
        val cursor = database.rawQuery("SELECT * FROM " + Db.CommentTable.TABLE_NAME, null)
        cursor.moveToFirst()
        return cursor
    }

    private fun insertCachedComment(cachedComment: CachedComment) {
        database.insertOrThrow(Db.CommentTable.TABLE_NAME, null,
                commentMapper.toContentValues(cachedComment))
    }

}