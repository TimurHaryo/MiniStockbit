package id.timtam.ministockbit.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.ajalt.timberkt.d
import id.timtam.core.abstraction.BaseFragmentBinding
import id.timtam.ministockbit.R
import id.timtam.ministockbit.databinding.FragmentMainBinding
import id.timtam.ministockbit.ui.main.adapter.MainMenuAdapter
import id.timtam.ministockbit.ui.search.SearchFragment
import id.timtam.ministockbit.ui.toptier.TopTierFragment

class MainFragment : BaseFragmentBinding<FragmentMainBinding>() {

    private val topTierFragment: TopTierFragment = TopTierFragment()
    private val searchFragment: SearchFragment = SearchFragment()

    private val fragments = listOf(topTierFragment, searchFragment)

    private val mainMenuAdapter: MainMenuAdapter by lazy { MainMenuAdapter(fragments, requireActivity()) }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override fun doWhenCreated() {
        d { "FRAG: Created" }
        setupBottomNavigation()
        setupFragmentContainer()
    }

    private fun setupBottomNavigation() {
        with(binding) {
            bottomNavMain.setOnItemSelectedListener { menuItem ->
                when(menuItem.itemId) {
                    R.id.topTierVolumeFragment -> {
                        fragmentContainerHome.setCurrentItem(fragments.indexOf(topTierFragment), false)
                        return@setOnItemSelectedListener true
                    }
                    R.id.searchFragment -> {
                        fragmentContainerHome.setCurrentItem(fragments.indexOf(searchFragment), false)
                        return@setOnItemSelectedListener true
                    }
                }

                return@setOnItemSelectedListener false
            }
        }
    }

    private fun setupFragmentContainer() {
        with(binding.fragmentContainerHome) {
            adapter = mainMenuAdapter
            isUserInputEnabled = false
            offscreenPageLimit = fragments.size
        }
    }
}