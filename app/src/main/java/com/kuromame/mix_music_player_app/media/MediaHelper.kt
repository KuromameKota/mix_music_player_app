package com.kuromame.mix_music_player_app.media

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream


class MediaHelper constructor(private val _context: Context) : IMediaHelper {
    private var context: Context

    init {
        context = _context
    }

    companion object {
        private var instance : MediaHelper? = null

        fun  instance(context: Context): MediaHelper {
            if (instance == null)
                instance = MediaHelper(context)

            return instance!!
        }
    }



    override fun scanTracks(): ArrayList<Track> {
        val array = ArrayList<Track>()

        if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            println("scanTracks")

            return array
        }

        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM_ID)

        val cursor = context.contentResolver.query(uri, projection, null, null, null)

        if(cursor != null) {
            cursor.moveToFirst()

            while(!cursor.isAfterLast) {
                val id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID)).toLong()
                val title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                val artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                val data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                val duration = Track.convertDuration(cursor.getString(cursor.getColumnIndex(
                        MediaStore.Audio.Media.DURATION)).toLong())
                val albumId = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)).toLong()

                cursor.moveToNext()

                array.add(Track(id, title, artist, data, duration, albumId))
            }

            cursor.close()
        }

        return array
    }

    override fun scanAlbums(): ArrayList<Album> {
        val array = ArrayList<Album>()

        val uri: Uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
                MediaStore.Audio.Albums._ID,
                MediaStore.Audio.Albums.ALBUM,
                MediaStore.Audio.Albums.ARTIST,
                MediaStore.Audio.Albums.ALBUM_ART)

        val sortOrder = "${MediaStore.Audio.Media.ALBUM} ASC"

        val cursor = context.contentResolver.query(uri, projection, null, null, sortOrder)

        if(cursor != null) {
            cursor.moveToFirst()
            while(!cursor.isAfterLast) {
                val id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums._ID)).toLong()
                val album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM))
                val artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST))
                val cover = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART))

                cursor.moveToNext()
                array.add(Album(id, album, artist, cover))
            }
            cursor.close()
        }

        return array
    }

    override fun scanAlbumTracks(albumID: Long): ArrayList<Track> {
        val array = ArrayList<Track>()

        val uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM_ID)

        val selection = "${MediaStore.Audio.Albums.ALBUM_ID} == $albumID"

        val sortOrder = "${MediaStore.Audio.AudioColumns.TITLE} COLLATE LOCALIZED ASC"

        val cursor: Cursor? = context.contentResolver.query(uri, projection, selection, null, sortOrder)

        if(cursor != null) {
            cursor.moveToFirst()
            while(!cursor.isAfterLast) {
                val id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID)).toLong()
                val title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                val artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                val data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                val duration = Track.convertDuration(cursor.getString(cursor.getColumnIndex(
                        MediaStore.Audio.Media.DURATION)).toLong())
                val albumId = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)).toLong()

                cursor.moveToNext()

                array.add(Track(id, title, artist, data, duration, albumId))
            }

            cursor.close()
        }

        return array
    }

    override fun scanArtists(): ArrayList<Artist> {
        val array = ArrayList<Artist>()

        val uri: Uri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
                MediaStore.Audio.Artists._ID,
                MediaStore.Audio.Artists.ARTIST,
                MediaStore.Audio.Artists.NUMBER_OF_ALBUMS,
                MediaStore.Audio.Artists.NUMBER_OF_TRACKS
        )

        val sortOrder = "${MediaStore.Audio.Artists.ARTIST} ASC"

        val cursor: Cursor? = context.contentResolver.query(uri, projection, null, null, sortOrder)

        if(cursor != null) {
            cursor.moveToFirst()
            while(!cursor.isAfterLast) {
                val id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists._ID)).toLong()
                val artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST))
                val albumsCount = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_ALBUMS))
                val tracksCount = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_TRACKS))

                cursor.moveToNext()

                array.add(Artist(id, artist, albumsCount, tracksCount))
            }

            cursor.close()
        }

        return array
    }

    override fun scanArtistTracks(artistID: Long): ArrayList<Track> {
        val array = ArrayList<Track>()

        val uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM_ID)

        val selection = "${MediaStore.Audio.Media.ARTIST_ID} == $artistID"

        val sortOrder = "${MediaStore.Audio.AudioColumns.TITLE} COLLATE LOCALIZED ASC"

        val cursor  = context.contentResolver.query(uri, projection, selection, null, sortOrder)

        if(cursor != null) {
            cursor.moveToFirst()
            while(!cursor.isAfterLast) {
                val id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID)).toLong()
                val title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                val artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                val data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                val duration = Track.convertDuration(cursor.getString(cursor.getColumnIndex(
                        MediaStore.Audio.Media.DURATION)).toLong())
                val albumId = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)).toLong()

                cursor.moveToNext()

                array.add(Track(id, title, artist, data, duration, albumId))
            }

            cursor.close()
        }

        return array
    }



    override fun scanGenres(): ArrayList<Genre> {
        val array = ArrayList<Genre>()

        val uri = MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
                MediaStore.Audio.Genres._ID,
                MediaStore.Audio.Genres.NAME
        )

        val sortOrder = "${MediaStore.Audio.Genres.NAME} ASC"

        val cursor = context.contentResolver.query(uri, projection, null, null, sortOrder)

        if(cursor != null) {
            cursor.moveToFirst()
            while(!cursor.isAfterLast) {
                val id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Genres._ID)).toLong()
                val name: String = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Genres.NAME))

                cursor.moveToNext()

                array.add(Genre(id, name))
            }

            cursor.close()
        }

        return array
    }

    override fun scanGenreTracks(genreID: Long): ArrayList<Track> {
        val array = ArrayList<Track>()

        val uri = android.provider.MediaStore.Audio.Genres.Members.getContentUri("external", genreID)

        val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM_ID)

        val selection = "${MediaStore.Audio.Media.IS_MUSIC}  != 0"

        val sortOrder = "${MediaStore.Audio.AudioColumns.TITLE} COLLATE LOCALIZED ASC"

        val cursor = context.contentResolver.query(uri, projection, selection, null, sortOrder)

        if(cursor != null) {
            cursor.moveToFirst()
            while(!cursor.isAfterLast) {
                val id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID)).toLong()
                val title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                val artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                val data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                val duration = Track.convertDuration(cursor.getString(cursor.getColumnIndex(
                        MediaStore.Audio.Media.DURATION)).toLong())
                val albumId = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)).toLong()

                cursor.moveToNext()

                array.add(Track(id, title, artist, data, duration, albumId))
            }

            cursor.close()
        }

        return array
    }

    override fun getAlbumImagePath(albumID: Long): String? {
        val uri = android.provider.MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Audio.Albums.ALBUM_ART)
        val selection = MediaStore.Audio.Albums._ID + "=?"
        val args = arrayOf(albumID.toString())

        val cursor = context.contentResolver.query(uri, projection, selection, args, null)

        var albumPath: String? = null

        if(cursor != null) {
            if(cursor.moveToFirst()) albumPath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART))
            cursor.close()
        }

        return albumPath
    }

    override fun loadItem(albumId: Long): Bitmap? {
        val sArtworkUri = Uri.parse("content://media/external/audio/albumart")
        val imageUri = Uri.withAppendedPath(sArtworkUri, albumId.toString())

        var bitmap: Bitmap? = null
        try {
            bitmap = decodeSampledBitmapFromResource(imageUri, 64, 64)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }

    private fun decodeSampledBitmapFromResource(imageUri: Uri, reqWidth: Int, reqHeight: Int): Bitmap? {
        var inputStream: InputStream ? = null
        return try {
            inputStream = context.contentResolver.openInputStream(imageUri)
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeStream(inputStream, null, options)
            options.inSampleSize = calculateInSampleSize(options, reqWidth,
                    reqHeight)
            options.inJustDecodeBounds = false
            inputStream = context.contentResolver.openInputStream(imageUri)
            BitmapFactory.decodeStream(inputStream, null, options)
        } catch (e: FileNotFoundException) {
            //     e.printStackTrace();
            null
        } finally {
            try {
                inputStream?.close()
            } catch (e: IOException) {
                //      e.printStackTrace();
            }
        }
    }

    fun calculateInSampleSize(
            options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        // Raw height and width of image
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while (halfHeight / inSampleSize >= reqHeight
                    && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}