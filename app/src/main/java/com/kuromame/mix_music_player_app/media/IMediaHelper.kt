package com.kuromame.mix_music_player_app.media

import android.graphics.Bitmap

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