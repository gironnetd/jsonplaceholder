package com.vlabs.jsonplaceholder.cache.db.constants.comments

import com.vlabs.jsonplaceholder.cache.db.Db

/**
 * Defines DB queries for the Bufferoos Table
 */
object CommentConstants {

    internal val QUERY_GET_ALL_COMMENTS = "SELECT * FROM " + Db.CommentTable.TABLE_NAME
    internal val QUERY_GET_COMMENTS_BY_POST_ID = "SELECT * FROM " + Db.CommentTable.TABLE_NAME + " WHERE " + Db.CommentTable.POST_ID + " = %d"
}