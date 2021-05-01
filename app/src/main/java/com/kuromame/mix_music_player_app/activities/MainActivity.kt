package com.kuromame.mix_music_player_app.activities

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.kuromame.mix_music_player_app.fragments.MainFragment
import com.kuromame.mix_music_player_app.fragments.PlayerFragment
import com.kuromame.mix_music_player_app.R
import com.kuromame.mix_music_player_app.media.IMediaHelper
import com.kuromame.mix_music_player_app.media.MediaHelper
import com.kuromame.mix_music_player_app.media.Track


interface IListener {
    fun onClickButton()
    fun scanTracks(): ArrayList<Track>
    fun getContext(): Context
}

class MainActivity() : AppCompatActivity(), IListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            val mainFragment = MainFragment()
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, mainFragment)
            fragmentTransaction.commit()
        }
    }

    override fun onClickButton() {
        val playerFragment = PlayerFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, playerFragment)
        fragmentTransaction.commit()
    }

    override fun scanTracks(): ArrayList<Track> {
        val mediaHelper = MediaHelper.instance(this)

        return  mediaHelper.scanTracks()
    }

    override fun getContext(): Context {
        return  this
    }
}
