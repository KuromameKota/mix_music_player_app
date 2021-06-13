package com.kuromame.mix_music_player_app.data.helpers

import android.graphics.Bitmap
import com.kuromame.mix_music_player_app.data.medias.Album
import com.kuromame.mix_music_player_app.data.medias.Artist
import com.kuromame.mix_music_player_app.data.medias.Genre
import com.kuromame.mix_music_player_app.data.medias.Track

interface IMediaHelper {
    fun scanTracks(): ArrayList<Track>

    fun scanAlbums(): ArrayList<Album>
    fun scanAlbumTracks(albumID: Long): ArrayList<Track>
    fun getAlbumImagePath(albumID: Long): String?

    fun scanArtists(): ArrayList<Artist>
    fun scanArtistTracks(artistID: Long): ArrayList<Track>

    fun scanGenres(): ArrayList<Genre>
    fun scanGenreTracks(genreID: Long): ArrayList<Track>
    fun loadItem(albumId: Long): Bitmap?
}