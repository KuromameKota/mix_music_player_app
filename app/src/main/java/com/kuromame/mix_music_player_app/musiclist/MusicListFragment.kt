package com.kuromame.mix_music_player_app.musiclist

import androidx.fragment.app.Fragment

class MusicListFragment : Fragment(), MusicListContract.View {
    override lateinit var presenter: MusicListContract.Presenter

    override var isActive = false
        get() = isAdded

    override fun showEmptyMusicList(){

    }

    override fun showLoadingMusicList(){

    }

    override fun showMusicList(){

    }

    companion object {
        fun newInstance() = MusicListFragment()
    }
}