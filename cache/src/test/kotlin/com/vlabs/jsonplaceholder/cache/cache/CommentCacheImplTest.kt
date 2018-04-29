package com.vlabs.jsonplaceholder.cache.cache

import com.vlabs.jsonplaceholder.cache.PreferencesHelper
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.DbOpenHelper
import com.vlabs.jsonplaceholder.cache.db.mapper.comments.CommentMapper
import com.vlabs.jsonplaceholder.cache.model.comments.CachedComment
import com.vlabs.jsonplaceholder.cache.model.comments.CommentCacheImpl
import com.vlabs.jsonplaceholder.cache.test.factory.CommentFactory
import com.vlabs.jsonplaceholder.data.mappers.comments.CommentEntityMapper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21))
class CommentCacheImplTest {

    private var entityMapper = CommentEntityMapper()
    private var mapper = CommentMapper()
    private var preferencesHelper = PreferencesHelper(RuntimeEnvironment.application)

    private val databaseHelper = CommentCacheImpl(DbOpenHelper(RuntimeEnvironment.application),
            entityMapper, mapper, preferencesHelper)

    @Before
    fun setup() {
        databaseHelper.getDatabase().rawQuery("DELETE FROM " + Db.CommentTable.TABLE_NAME, null)
    }

    @Test
    fun clearTablesCompletes() {
        val testObserver = databaseHelper.clearComments().test()
        testObserver.assertComplete()
    }

    //<editor-fold desc="Save Comments">
    @Test
    fun saveCommentsCompletes() {
        val commentEntities = CommentFactory.makeCommentEntityList(2)

        val testObserver = databaseHelper.saveComments(commentEntities).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveCommentsSavesData() {
        val commentCount = 2
        val commentEntities = CommentFactory.makeCommentEntityList(commentCount)

        databaseHelper.saveComments(commentEntities).test()
        checkNumRowsInCommentsTable(commentCount)
    }
    //</editor-fold>

    //<editor-fold desc="Get Comments">
    @Test
    fun getCommentsCompletes() {
        val testObserver = databaseHelper.getComments().test()
        testObserver.assertComplete()
    }

    @Test
    fun getCommentsReturnsData() {
        val commentEntities = CommentFactory.makeCommentEntityList(2)
        val cachedComments = mutableListOf<CachedComment>()
        commentEntities.forEach {
            cachedComments.add(entityMapper.mapToCached(it))
        }
        insertComments(cachedComments)

        val testObserver = databaseHelper.getComments().test()
        testObserver.assertValue(commentEntities)
    }
    //</editor-fold>

    private fun insertComments(cachedComments: List<CachedComment>) {
        val database = databaseHelper.getDatabase()
        cachedComments.forEach {
            database.insertOrThrow(Db.CommentTable.TABLE_NAME, null,
                    mapper.toContentValues(it))
        }
    }

    private fun checkNumRowsInCommentsTable(expectedRows: Int) {
        val commentsCursor = databaseHelper.getDatabase().query(Db.CommentTable.TABLE_NAME,
                null, null, null, null, null, null)
        commentsCursor.moveToFirst()
        val numberOfRows = commentsCursor.count
        commentsCursor.close()
        assertEquals(expectedRows, numberOfRows)
    }

}