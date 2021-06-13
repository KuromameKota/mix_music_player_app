package com.kuromame.mix_music_player_app.musiclist

class MusicListPresenter( private val musicListView: MusicListContract.View, override var isMusicMissing: Boolean) : MusicListContract.Presenter {

    init {
        musicListView.presenter = this
    }

    override fun start() {
        TODO("Not yet implemented")
    }
}