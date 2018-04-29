package com.vlabs.jsonplaceholder.cache.db.mapper.comments

import android.content.ContentValues
import android.database.Cursor
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.mapper.ModelDbMapper
import com.vlabs.jsonplaceholder.cache.model.comments.CachedComment
import javax.inject.Inject

class CommentMapper @Inject constructor(): ModelDbMapper<CachedComment> {

    /**
     * Construct an instance of [ContentValues] using the given [CachedComment]
     */
    override fun toContentValues(model: CachedComment): ContentValues {
        val values = ContentValues()
        values.put(Db.CommentTable.POST_ID, model.postId)
        values.put(Db.CommentTable.ID, model.id.toInt())
        values.put(Db.CommentTable.NAME, model.name)
        values.put(Db.CommentTable.EMAIL, model.email)
        values.put(Db.CommentTable.BODY, model.body)

        return values
    }

    /**
     * Parse the cursor creating a [CachedComment] instance.
     */
    override fun parseCursor(cursor: Cursor): CachedComment {
        val postId = cursor.getInt(cursor.getColumnIndexOrThrow(Db.CommentTable.POST_ID))
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(Db.CommentTable.ID))
        val name = cursor.getString(cursor.getColumnIndexOrThrow(Db.CommentTable.NAME))
        val email = cursor.getString(cursor.getColumnIndexOrThrow(Db.CommentTable.EMAIL))

        val body = cursor.getString(cursor.getColumnIndexOrThrow(Db.CommentTable.BODY))

        return CachedComment(postId.toString(), id.toString(), name,email, body)
    }

}
