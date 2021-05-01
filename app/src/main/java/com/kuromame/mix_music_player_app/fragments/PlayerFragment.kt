package com.kuromame.mix_music_player_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kuromame.mix_music_player_app.R

class PlayerFragment : Fragment(){
    companion object {
        fun instance () = PlayerFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
}