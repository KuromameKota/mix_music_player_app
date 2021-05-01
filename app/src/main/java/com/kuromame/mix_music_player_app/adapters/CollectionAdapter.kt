package com.kuromame.mix_music_player_app.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kuromame.mix_music_player_app.fragments.MusicListFragment
import com.kuromame.mix_music_player_app.fragments.PlayListFragment

class CollectionAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    private val fragments: ArrayList<Fragment> = arrayListOf(
        MusicListFragment.instance(),
        PlayListFragment.instance()
    )

    fun getItem(position: Int): Fragment = fragments[position]

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}