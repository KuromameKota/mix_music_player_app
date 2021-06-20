package com.kuromame.mix_music_player_app.musiclist

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.kuromame.mix_music_player_app.R

class MusicListActivity : AppCompatActivity() {
    private lateinit var musicListPresenter: MusicListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.music_list)

        val musicListFragment = supportFragmentManager.findFragmentById(R.id.music_list)
            as MusicListFragment? ?: MusicListFragment.newInstance().also {
        }

        musicListPresenter = MusicListPresenter(musicListFragment, false);
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState.apply {
            putBoolean(SHOULD_LOAD_DATA_FROM_REPO_KEY, musicListPresenter.isMusicMissing)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val SHOULD_LOAD_DATA_FROM_REPO_KEY = "SHOULD_LOAD_DATA_FROM_REPO_KEY"
    }
}