package com.kuromame.mix_music_player_app.musiclist

import com.kuromame.mix_music_player_app.BasePresenter
import com.kuromame.mix_music_player_app.BaseView

interface MusicListContract {
    interface  View : BaseView<Presenter>{
        var isActive: Boolean
        fun showEmptyMusicList()
        fun showLoadingMusicList()
        fun showMusicList()

    }

    interface Presenter : BasePresenter {
        var isMusicMissing: Boolean
    }
}