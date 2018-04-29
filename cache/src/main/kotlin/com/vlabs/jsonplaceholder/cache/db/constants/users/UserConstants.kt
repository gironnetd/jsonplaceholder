package com.vlabs.jsonplaceholder.cache.db.constants.users

import com.vlabs.jsonplaceholder.cache.db.Db

/**
 * Defines DB queries for the Bufferoos Table
 */
object UserConstants {

    internal val QUERY_GET_ALL_USERS = "SELECT * FROM " + Db.UserTable.TABLE_NAME

}