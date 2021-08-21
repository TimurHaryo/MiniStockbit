package id.timtam.ministockbit.ui.toptier

import androidx.lifecycle.viewModelScope
import id.timtam.core.abstraction.BaseViewModel
import id.timtam.core.dispatcher.DispatcherProvider
import id.timtam.core.exception.Failure
import id.timtam.core.extension.onError
import id.timtam.core.extension.onSuccess
import id.timtam.ministockbit.domain.model.TopTierVolume
import id.timtam.ministockbit.domain.usecase.GetTopTierVolumeUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TopTierViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val getTopTierVolumeUseCase: GetTopTierVolumeUseCase
) : BaseViewModel<TopTierViewModel.TopTierUI>() {

    sealed class TopTierUI {
        object Loading: TopTierUI()
        data class Success(val data: List<TopTierVolume>): TopTierUI()
        data class FailedLoadInitial(val failure: Failure): TopTierUI()
        data class FailedLoadMore(val failure: Failure, val lastPage: Int): TopTierUI()
    }

    fun loadFirstTier(limit: Int) {
        _uiState.value = TopTierUI.Loading
        viewModelScope.launch(dispatcherProvider.io) {
            val param = GetTopTierVolumeUseCase.Query(
                page = 1,
                limit = limit
            )

            getTopTierVolumeUseCase.run(param)
                .onSuccess {
                    withContext(dispatcherProvider.main) {
                        _uiState.value = TopTierUI.Success(it)
                    }
                }
                .onError {
                    withContext(dispatcherProvider.main) {
                        _uiState.value = TopTierUI.FailedLoadInitial(it)
                    }
                }
        }
    }

    fun loadMoreTier(page: Int, limit: Int) {
        _uiState.value = TopTierUI.Loading
        viewModelScope.launch(dispatcherProvider.io) {
            val param = GetTopTierVolumeUseCase.Query(
                page = page,
                limit = limit
            )

            getTopTierVolumeUseCase.run(param)
                .onSuccess {
                    withContext(dispatcherProvider.main) {
                        _uiState.value = TopTierUI.Success(it)
                    }
                }
                .onError {
                    withContext(dispatcherProvider.main) {
                        if (page != 1) {
                            _uiState.value = TopTierUI.FailedLoadMore(it, page)
                        }
                    }
                }
        }
    }
}