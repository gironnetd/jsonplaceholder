package com.vlabs.jsonplaceholder.cache.db.mapper.users

import android.content.ContentValues
import android.database.Cursor
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.mapper.ModelDbMapper
import com.vlabs.jsonplaceholder.cache.model.users.CachedUser
import javax.inject.Inject

class UserMapper @Inject constructor(): ModelDbMapper<CachedUser> {

    /**
     * Construct an instance of [ContentValues] using the given [CachedUser]
     */
    override fun toContentValues(model: CachedUser): ContentValues {
        val values = ContentValues()
        values.put(Db.UserTable.USER_ID, model.userId.toString())
        values.put(Db.UserTable.NAME, model.name)
        values.put(Db.UserTable.USERNAME, model.userName)
        values.put(Db.UserTable.EMAIL, model.email)
        values.put(Db.UserTable.PHONE, model.phone)
        values.put(Db.UserTable.WEBSITE, model.website)

        return values
    }

    /**
     * Parse the cursor creating a [CachedUser] instance.
     */
    override fun parseCursor(cursor: Cursor): CachedUser {
        val userId = cursor.getInt(cursor.getColumnIndexOrThrow(Db.UserTable.USER_ID))
        val name = cursor.getString(cursor.getColumnIndexOrThrow(Db.UserTable.NAME))
        val userName = cursor.getString(cursor.getColumnIndexOrThrow(Db.UserTable.USERNAME))
        val email = cursor.getString(cursor.getColumnIndexOrThrow(Db.UserTable.EMAIL))
        val phone = cursor.getString(cursor.getColumnIndexOrThrow(Db.UserTable.PHONE))
        val website = cursor.getString(cursor.getColumnIndexOrThrow(Db.UserTable.WEBSITE))

        return CachedUser(userId, name, userName, email, phone, website)
    }

}

