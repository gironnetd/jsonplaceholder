package com.vlabs.jsonplaceholder.cache.db

/**
 * This class defines the tables found within the application Database. All table
 * definitions should contain column names and a sequence for creating the table.
 */
object Db {

    object UserTable {
        const val TABLE_NAME = "users"

        const val USER_ID = "user_id"
        const val NAME = "name"
        const val USERNAME = "username"
        const val EMAIL = "email"
        const val ADRESS = "adress"
        const val PHONE = "phone"
        const val WEBSITE = "website"
        const val COMPANY = "company"

        const val CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        USER_ID + " TEXT," +
                        NAME + " TEXT ," +
                        USERNAME + " TEXT," +
                        EMAIL + " TEXT," +
                        PHONE + " TEXT," +
                        WEBSITE + " TEXT" +
                        "); "
    }

    object AdressTable {
        const val TABLE_NAME = "address"
        const val STREET = "street"
        const val SUITE = "suite"
        const val CITY = "city"
        const val ZIPCODE = "zipcode"
    }

    object GeoTable {

    }

    object PostTable {
        const val TABLE_NAME = "posts"
        const val USER_ID = "user_id"
        const val ID = "id"
        const val TITLE = "title"
        const val BODY = "body"

        const val CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        USER_ID + " INTEGER ," +
                        ID + " INTEGER NOT NULL," +
                        TITLE + " TEXT," +
                        BODY + " TEXT" +
                        "); "
    }

    object CommentTable {

        const val TABLE_NAME = "comments"
        const val POST_ID = "post_id"
        const val ID = "id"
        const val NAME = "name"
        const val EMAIL = "email"
        const val BODY = "body"

        const val CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        POST_ID + " INTEGER ," +
                        ID + " INTEGER NOT NULL," +
                        NAME + " TEXT," +
                        EMAIL + " TEXT," +
                        BODY + " TEXT" +
                        "); "
    }

    object AlbumTable {
        const val TABLE_NAME = "albums"
        const val USER_ID = "user_id"
        const val ID = "id"
        const val TITLE = "title"

        const val CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        USER_ID + " INTEGER ," +
                        ID + " INTEGER," +
                        TITLE + " TEXT" +
                        "); "
    }

    object PhotoTable {

        const val TABLE_NAME = "photos"
        const val ALBUM_ID = "album_id"
        const val ID = "id"
        const val TITLE = "title"
        const val URL = "url"
        const val THUMBNAIL_URL = "thumbnail_url"

        const val CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        ALBUM_ID + " INTEGER ," +
                        ID + " ID," +
                        TITLE + " TEXT," +
                        URL + " TEXT," +
                        THUMBNAIL_URL + " TEXT" +
                        "); "

    }

    object TodoTable {

        const val TABLE_NAME = "todos"
        const val USER_ID = "user_id"
        const val ID = "id"
        const val TITLE = "title"
        const val COMPLETED = "completed"

        const val CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        USER_ID + " INTEGER ," +
                        ID + " TEXT NOT NULL," +
                        TITLE + " TEXT," +
                        COMPLETED + " TEXT" +
                        "); "
    }
}