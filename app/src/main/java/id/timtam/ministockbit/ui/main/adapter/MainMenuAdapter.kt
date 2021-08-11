package id.timtam.ministockbit.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainMenuAdapter(
    private val fragments: List<Fragment>,
    fa: FragmentActivity
) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}