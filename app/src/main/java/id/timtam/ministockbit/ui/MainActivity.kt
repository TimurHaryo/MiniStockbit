package id.timtam.ministockbit.ui

import android.view.LayoutInflater
import id.timtam.core.abstraction.BaseActivityBinding
import id.timtam.ministockbit.databinding.ActivityMainBinding

class MainActivity : BaseActivityBinding<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setupView() {  }
}