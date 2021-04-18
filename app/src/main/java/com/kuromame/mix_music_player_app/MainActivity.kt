package com.kuromame.mix_music_player_app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


interface MyListener {
    fun onClickButton()
}

class MainActivity() : AppCompatActivity(), MyListener {

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
}
