package id.timtam.ministockbit.di.view

import id.timtam.core.dispatcher.DispatcherProvider
import id.timtam.core.dispatcher.DispatcherProviderImpl
import id.timtam.ministockbit.ui.toptier.TopTierViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelComponent = module {
    viewModel { TopTierViewModel(provideDispatcherProvider(), get()) }

}

private fun provideDispatcherProvider(): DispatcherProvider = DispatcherProviderImpl()