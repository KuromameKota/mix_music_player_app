package com.kuromame.mix_music_player_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class MusicListFragment : Fragment(){
    companion object {
        fun instance () = MusicListFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.music_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
}