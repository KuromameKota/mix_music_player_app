package com.kuromame.mix_music_player_app.media

data class Genre(val id: Long,
                 val name: String) {
    override fun equals(other: Any?): Boolean {
        return id == (other as Genre).id
    }
}