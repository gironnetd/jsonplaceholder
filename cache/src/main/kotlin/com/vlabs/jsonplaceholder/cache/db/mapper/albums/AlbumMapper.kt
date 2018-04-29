package com.vlabs.jsonplaceholder.cache.db.mapper.albums

import android.content.ContentValues
import android.database.Cursor
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.mapper.ModelDbMapper
import com.vlabs.jsonplaceholder.cache.model.albums.CachedAlbum
import javax.inject.Inject

class AlbumMapper @Inject constructor(): ModelDbMapper<CachedAlbum> {

    /**
     * Construct an instance of [ContentValues] using the given [CachedAlbum]
     */
    override fun toContentValues(model: CachedAlbum): ContentValues {
        val values = ContentValues()
        values.put(Db.AlbumTable.USER_ID, model.userId.toInt())
        values.put(Db.AlbumTable.ID, model.id.toInt())
        values.put(Db.AlbumTable.TITLE, model.title)

        return values
    }

    /**
     * Parse the cursor creating a [CachedAlbum] instance.
     */
    override fun parseCursor(cursor: Cursor): CachedAlbum {
        val userId = cursor.getInt(cursor.getColumnIndexOrThrow(Db.AlbumTable.USER_ID))
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(Db.AlbumTable.ID))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(Db.AlbumTable.TITLE))

        return CachedAlbum(userId, id, title)
    }

}


