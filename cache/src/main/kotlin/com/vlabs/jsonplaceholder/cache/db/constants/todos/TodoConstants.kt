package com.vlabs.jsonplaceholder.cache.db.constants.todos

import com.vlabs.jsonplaceholder.cache.db.Db

/**
 * Defines DB queries for the Bufferoos Table
 */
object TodoConstants {

    internal val QUERY_GET_ALL_TODOS = "SELECT * FROM " + Db.TodoTable.TABLE_NAME
    internal val QUERY_GET_TODOS_BY_USER_ID = "SELECT * FROM " + Db.TodoTable.TABLE_NAME + " WHERE " + Db.TodoTable.USER_ID + " = %d"
}