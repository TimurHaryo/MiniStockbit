package id.timtam.ministockbit.ui.toptier

import id.timtam.core.abstraction.BaseViewModel
import id.timtam.core.dispatcher.DispatcherProvider
import id.timtam.core.exception.Failure
import id.timtam.ministockbit.domain.model.TopTierVolume
import id.timtam.ministockbit.domain.usecase.GetTopTierVolumeUseCase

class TopTierViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val getTopTierVolumeUseCase: GetTopTierVolumeUseCase
) : BaseViewModel<TopTierViewModel.TopTierUI>() {

    sealed class TopTierUI {
        object Loading: TopTierUI()
        data class Success(val data: List<TopTierVolume>): TopTierUI()
        data class Failed(val failure: Failure): TopTierUI()
    }
}