package id.timtam.ministockbit.di.view

import id.timtam.ministockbit.ui.toptier.TopTierViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelComponent = module {
    viewModel { TopTierViewModel(get(), get()) }
}