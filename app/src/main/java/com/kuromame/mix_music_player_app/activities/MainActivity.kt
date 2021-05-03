package com.kuromame.mix_music_player_app.activities

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.kuromame.mix_music_player_app.fragments.MainFragment
import com.kuromame.mix_music_player_app.fragments.PlayerFragment
import com.kuromame.mix_music_player_app.R
import com.kuromame.mix_music_player_app.media.IMediaHelper
import com.kuromame.mix_music_player_app.media.MediaHelper
import com.kuromame.mix_music_player_app.media.RuntimePermissionUtils
import com.kuromame.mix_music_player_app.media.Track
import java.io.File


interface IListener {
    fun onClickButton()
    fun scanTracks(): ArrayList<Track>
    fun getContext(): Context
}

class MainActivity() : AppCompatActivity(), IListener {
    private val PERMISSIONS_REQUEST_CODE = 100

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

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // 許可されている
            File(externalStoragePath()).listFiles().map { file ->
                println(file.path)
            }
        } else {
            // 許可されていないので許可ダイアログを表示する
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSIONS_REQUEST_CODE)
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
        println("scanTracks")

        return mediaHelper.scanTracks()
    }

    override fun getContext(): Context {
        return  this
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSIONS_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    File(externalStoragePath()).listFiles().map { file ->
                        println(file.name)
                    }
                } else {
                    Handler().post(Runnable {
                        RuntimePermissionUtils().showAlertDialog(supportFragmentManager, "ストレージの読み込み")
                    })
                }
                return
            }
        }
    }

    private fun externalStoragePath(): String {
        return Environment.getExternalStorageDirectory().absolutePath
    }
}
