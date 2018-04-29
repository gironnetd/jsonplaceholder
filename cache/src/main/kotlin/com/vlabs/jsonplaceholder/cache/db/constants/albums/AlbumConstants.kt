package com.vlabs.jsonplaceholder.cache.db.constants.albums

import com.vlabs.jsonplaceholder.cache.db.Db

/**
 * Defines DB queries for the Bufferoos Table
 */
object AlbumConstants {

    internal val QUERY_GET_ALL_ALBUMS = "SELECT * FROM " + Db.AlbumTable.TABLE_NAME
    internal val QUERY_GET_ALBUMS_BY_USER_ID = "SELECT * FROM " + Db.AlbumTable.TABLE_NAME + " WHERE " + Db.AlbumTable.USER_ID + " = %d"
}