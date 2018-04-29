package com.vlabs.jsonplaceholder.cache.db.mapper.photos

import android.content.ContentValues
import android.database.Cursor
import com.vlabs.jsonplaceholder.cache.db.Db
import com.vlabs.jsonplaceholder.cache.db.mapper.ModelDbMapper
import com.vlabs.jsonplaceholder.cache.model.photos.CachedPhoto
import javax.inject.Inject

class PhotoMapper @Inject constructor(): ModelDbMapper<CachedPhoto> {

    /**
     * Construct an instance of [ContentValues] using the given [CachedPhoto]
     */
    override fun toContentValues(model: CachedPhoto): ContentValues {
        val values = ContentValues()
        values.put(Db.PhotoTable.ALBUM_ID, model.albumId)
        values.put(Db.PhotoTable.ID, model.id)
        values.put(Db.PhotoTable.TITLE, model.title)
        values.put(Db.PhotoTable.URL, model.url)
        values.put(Db.PhotoTable.THUMBNAIL_URL, model.thumbnailUrl)

        return values
    }

    /**
     * Parse the cursor creating a [CachedBufferoo] instance.
     */
    override fun parseCursor(cursor: Cursor): CachedPhoto {
        val albumId = cursor.getInt(cursor.getColumnIndexOrThrow(Db.PhotoTable.ALBUM_ID))
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(Db.PhotoTable.ID))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(Db.PhotoTable.TITLE))
        val url = cursor.getString(cursor.getColumnIndexOrThrow(Db.PhotoTable.URL))
        val thumbnailUrl = cursor.getString(cursor.getColumnIndexOrThrow(Db.PhotoTable.THUMBNAIL_URL))

        return CachedPhoto(albumId.toString(), id.toString(), title, url, thumbnailUrl)
    }
}
