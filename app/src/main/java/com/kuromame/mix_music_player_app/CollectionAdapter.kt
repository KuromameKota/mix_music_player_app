package com.kuromame.mix_music_player_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CollectionAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    private val fragments: ArrayList<Fragment> = arrayListOf(
            MusicListFragment.instance(),
            PlayListFragment.instance()
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}