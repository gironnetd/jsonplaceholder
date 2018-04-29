package com.vlabs.jsonplaceholder.cache.db.mapper.todos

import android.content.ContentValues
import android.database.Cursor
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.mapper.ModelDbMapper
import com.vlabs.jsonplaceholder.cache.model.todos.CachedTodo
import javax.inject.Inject

class TodoMapper @Inject constructor(): ModelDbMapper<CachedTodo> {

    /**
     * Construct an instance of [ContentValues] using the given [CachedTodo]
     */
    override fun toContentValues(model: CachedTodo): ContentValues {
        val values = ContentValues()
        values.put(Db.TodoTable.USER_ID, model.userId.toInt())
        values.put(Db.TodoTable.ID, model.id.toInt())
        values.put(Db.TodoTable.TITLE, model.title)
        values.put(Db.TodoTable.COMPLETED, model.completed)

        return values
    }

    /**
     * Parse the cursor creating a [CachedTodo] instance.
     */
    override fun parseCursor(cursor: Cursor): CachedTodo {
        val userId = cursor.getInt(cursor.getColumnIndexOrThrow(Db.TodoTable.USER_ID))
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(Db.TodoTable.ID))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(Db.TodoTable.TITLE))
        val completed = cursor.getString(cursor.getColumnIndexOrThrow(Db.TodoTable.COMPLETED))

        return CachedTodo(userId, id, title,completed)
    }
}
