package com.kuromame.mix_music_player_app.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.kuromame.mix_music_player_app.R
import com.kuromame.mix_music_player_app.activities.IListener

class PlayListFragment : Fragment(){
    private lateinit var testButton: Button
    private lateinit var listener: IListener

    companion object {
        fun instance () = PlayListFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.play_list, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IListener) {
            listener = context as IListener
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        testButton = view.findViewById<Button>(R.id.test_button)
        testButton.setOnClickListener {
            listener.onClickButton();

            val fragmentManager = fragmentManager;

            if (fragmentManager != null) {
                //val fragmentTransaction = fragmentManager.beginTransaction()
                //fragmentTransaction.addToBackStack(null)
                //fragmentTransaction.replace(R.id.container, PlayerFragment.instance())
                //fragmentTransaction.commit()
            }
        }
    }
}