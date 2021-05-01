package com.kuromame.mix_music_player_app.media

data class Album(val id: Long,
                 val album: String,
                 val artist: String,
                 val art: String?) {


    override fun equals(other: Any?): Boolean {
        return id == (other as Album).id
    }
}