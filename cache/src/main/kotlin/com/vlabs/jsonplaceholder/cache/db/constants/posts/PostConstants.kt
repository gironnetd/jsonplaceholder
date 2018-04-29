package com.vlabs.jsonplaceholder.cache.db.constants.posts

import com.vlabs.jsonplaceholder.cache.db.Db

/**
 * Defines DB queries for the Bufferoos Table
 */
object PostConstants {

    internal val QUERY_GET_ALL_POSTS = "SELECT * FROM " + Db.PostTable.TABLE_NAME
    internal val QUERY_GET_POSTS_BY_USER_ID = "SELECT * FROM " + Db.PostTable.TABLE_NAME + " WHERE " + Db.PostTable.USER_ID + " = %d"
}