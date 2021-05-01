package com.kuromame.mix_music_player_app.media

data class Artist(val id: Long,
                  val artist: String,
                  val albumCount: String,
                  val trackCount: String) {
    override fun equals(other: Any?): Boolean {
        return id == (other as Artist).id
    }
}