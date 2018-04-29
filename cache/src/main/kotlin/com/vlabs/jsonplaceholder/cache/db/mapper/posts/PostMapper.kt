package com.vlabs.jsonplaceholder.cache.db.mapper.posts

import android.content.ContentValues
import android.database.Cursor
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.mapper.ModelDbMapper
import com.vlabs.jsonplaceholder.cache.model.users.CachedPost
import javax.inject.Inject

class PostMapper @Inject constructor(): ModelDbMapper<CachedPost> {

    /**
     * Construct an instance of [ContentValues] using the given [CachedPost]
     */
    override fun toContentValues(model: CachedPost): ContentValues {
        val values = ContentValues()
        values.put(Db.PostTable.USER_ID, model.userId)
        values.put(Db.PostTable.ID, model.id)
        values.put(Db.PostTable.TITLE, model.title)
        values.put(Db.PostTable.BODY, model.body)

        return values
    }

    /**
     * Parse the cursor creating a [CachedPost] instance.
     */
    override fun parseCursor(cursor: Cursor): CachedPost {
        val userId = cursor.getInt(cursor.getColumnIndexOrThrow(Db.PostTable.USER_ID))
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(Db.PostTable.ID))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(Db.PostTable.TITLE))
        val body = cursor.getString(cursor.getColumnIndexOrThrow(Db.PostTable.BODY))

        return CachedPost(userId.toString(), id.toString(), title, body)
    }

}



