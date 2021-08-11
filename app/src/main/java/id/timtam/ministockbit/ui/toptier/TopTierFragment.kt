package id.timtam.ministockbit.ui.toptier

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.ajalt.timberkt.d
import id.timtam.core.abstraction.BaseFragmentBinding
import id.timtam.ministockbit.databinding.FragmentTopTierBinding

class TopTierFragment : BaseFragmentBinding<FragmentTopTierBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTopTierBinding
        get() = FragmentTopTierBinding::inflate

    override fun doWhenCreated() {
        d { "FRAG: Created" }
    }

    override fun doWhenDisplayed() {
        d { "FRAG: Resumed/Displayed" }
    }
}