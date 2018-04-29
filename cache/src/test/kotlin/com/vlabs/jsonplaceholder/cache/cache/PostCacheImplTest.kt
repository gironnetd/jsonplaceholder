package com.vlabs.jsonplaceholder.cache.cache

import com.vlabs.jsonplaceholder.cache.PreferencesHelper
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.DbOpenHelper
import com.vlabs.jsonplaceholder.cache.db.mapper.posts.PostMapper
import com.vlabs.jsonplaceholder.cache.mapper.posts.PostEntityMapper
import com.vlabs.jsonplaceholder.cache.model.users.CachedPost
import com.vlabs.jsonplaceholder.cache.model.users.PostCacheImpl
import com.vlabs.jsonplaceholder.cache.test.factory.PostFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21))
class PostCacheImplTest {

    private var entityMapper = PostEntityMapper()
    private var mapper = PostMapper()
    private var preferencesHelper = PreferencesHelper(RuntimeEnvironment.application)

    private val databaseHelper = PostCacheImpl(DbOpenHelper(RuntimeEnvironment.application),
            entityMapper, mapper, preferencesHelper)

    @Before
    fun setup() {
        databaseHelper.getDatabase().rawQuery("DELETE FROM " + Db.PostTable.TABLE_NAME, null)
    }

    @Test
    fun clearTablesCompletes() {
        val testObserver = databaseHelper.clearPosts().test()
        testObserver.assertComplete()
    }

    //<editor-fold desc="Save Posts">
    @Test
    fun savePostsCompletes() {
        val postEntities = PostFactory.makePostEntityList(2)

        val testObserver = databaseHelper.savePosts(postEntities).test()
        testObserver.assertComplete()
    }

    @Test
    fun savePostsSavesData() {
        val postCount = 2
        val postEntities = PostFactory.makePostEntityList(postCount)

        databaseHelper.savePosts(postEntities).test()
        checkNumRowsInPostsTable(postCount)
    }
    //</editor-fold>

    //<editor-fold desc="Get Posts">
    @Test
    fun getPostsCompletes() {
        val testObserver = databaseHelper.getPosts().test()
        testObserver.assertComplete()
    }

    @Test
    fun getPostsReturnsData() {
        val postEntities = PostFactory.makePostEntityList(2)
        val cachedPosts = mutableListOf<CachedPost>()
        postEntities.forEach {
            cachedPosts.add(entityMapper.mapToCached(it))
        }
        insertPosts(cachedPosts)

        val testObserver = databaseHelper.getPosts().test()
        testObserver.assertValue(postEntities)
    }
    //</editor-fold>

    private fun insertPosts(cachedPosts: List<CachedPost>) {
        val database = databaseHelper.getDatabase()
        cachedPosts.forEach {
            database.insertOrThrow(Db.PostTable.TABLE_NAME, null,
                    mapper.toContentValues(it))
        }
    }

    private fun checkNumRowsInPostsTable(expectedRows: Int) {
        val postsCursor = databaseHelper.getDatabase().query(Db.PostTable.TABLE_NAME,
                null, null, null, null, null, null)
        postsCursor.moveToFirst()
        val numberOfRows = postsCursor.count
        postsCursor.close()
        assertEquals(expectedRows, numberOfRows)
    }

}