package com.vlabs.jsonplaceholder.cache.db.constants.photos

import com.vlabs.jsonplaceholder.cache.db.Db

/**
 * Defines DB queries for the Bufferoos Table
 */
object PhotoConstants {

    internal val QUERY_GET_ALL_PHOTOS = "SELECT * FROM " + Db.PhotoTable.TABLE_NAME
    internal val QUERY_GET_PHOTOS_BY_ALBUM_ID = "SELECT * FROM " + Db.PhotoTable.TABLE_NAME + " WHERE " + Db.PhotoTable.ALBUM_ID + " = %d"
}