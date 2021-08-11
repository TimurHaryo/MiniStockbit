package id.timtam.ministockbit.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.ajalt.timberkt.d
import id.timtam.core.abstraction.BaseFragmentBinding
import id.timtam.ministockbit.databinding.FragmentSearchBinding

class SearchFragment : BaseFragmentBinding<FragmentSearchBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate

    override fun doWhenCreated() {
        d { "FRAG: Created" }
    }

    override fun doOnceWhenDisplayed() {
        d { "FRAG: Resumed/Displayed" }
    }
}