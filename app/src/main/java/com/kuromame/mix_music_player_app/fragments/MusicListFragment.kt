package com.kuromame.mix_music_player_app.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.kuromame.mix_music_player_app.R
import com.kuromame.mix_music_player_app.activities.IListener

class MusicListFragment : Fragment(){
    private lateinit var listener: IListener

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IListener) {
            listener = context as IListener
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tracks = listener.scanTracks();
        val scrollView = view.findViewById<ScrollView>(R.id.scroll_view)

        for ( i in 0..tracks.size )
        {
            val musicListLayout = layoutInflater.inflate(R.layout.music_list_layout, null)

            val track = tracks[i]

            val titleText = musicListLayout.findViewById<TextView>(R.id.music_title_text)
            titleText.text = track.title

            val artistNameText = musicListLayout.findViewById<TextView>(R.id.artist_name_text_view)
            artistNameText.text = track.artist

            val durationText = musicListLayout.findViewById<TextView>(R.id.duration_text)
            durationText.text = track.duration

            scrollView.addView(musicListLayout)
        }
    }
}