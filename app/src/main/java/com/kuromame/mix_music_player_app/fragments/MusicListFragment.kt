package com.kuromame.mix_music_player_app.fragments

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
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
        val scrollViewLayout = view.findViewById<LinearLayout>(R.id.scroll_view_layout)
        println("MusicListFragment tracks.size")

        println(tracks.size)

        for ( track in tracks )
        {
            val musicListLayout = layoutInflater.inflate(R.layout.music_list_layout, null)
            if (musicListLayout == null)
            {
                println("music_list_layout is null")
            }
            val imageView = musicListLayout.findViewById<ImageView>(R.id.album_image_view)
            if (imageView != null)
            {
                val bitmap = listener.getAlbumImage(track.albumId)

                if (bitmap != null)
                {
                    imageView.setImageBitmap(bitmap)
                    println("set image")
                }
                else
                {
                    println("no image")
                }
            }

            val titleText = musicListLayout.findViewById<TextView>(R.id.music_title_text)
            if (titleText != null)
            {
                titleText.text = track.title
            }
            else
            {
                println("titleText is null")
            }

            val artistNameText = musicListLayout.findViewById<TextView>(R.id.artist_text)
            if (artistNameText != null)
            {
                artistNameText.text = track.artist
            }
            else
            {
                println("artistNameText is null")
            }

            val durationText = musicListLayout.findViewById<TextView>(R.id.duration_text)
            if (durationText != null)
            {
                durationText.text = track.duration
            }
            else
            {
                println("durationText is null")
            }

            scrollViewLayout.addView(musicListLayout)
        }
    }
}