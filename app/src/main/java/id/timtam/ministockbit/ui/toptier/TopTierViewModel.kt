package id.timtam.ministockbit.ui.toptier

import androidx.lifecycle.viewModelScope
import id.timtam.core.abstraction.BaseViewModel
import id.timtam.core.abstraction.PaginableViewModel
import id.timtam.core.dispatcher.DispatcherProvider
import id.timtam.core.exception.Failure
import id.timtam.core.extension.onError
import id.timtam.core.extension.onSuccess
import id.timtam.core.util.PagingConstants
import id.timtam.ministockbit.domain.model.TopTierVolume
import id.timtam.ministockbit.domain.usecase.GetTopTierVolumeUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TopTierViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val getTopTierVolumeUseCase: GetTopTierVolumeUseCase
) : BaseViewModel<TopTierViewModel.TopTierUI>(), PaginableViewModel {

    sealed class TopTierUI {
        object Loading: TopTierUI()
        data class Success(val data: List<TopTierVolume>): TopTierUI()
        data class Failed(val failure: Failure): TopTierUI()
    }

    override var dataSize: Int = -1

    override fun isLastPage() : Boolean {
        return (PagingConstants.BATCH_SIZE > dataSize) && (dataSize != -1)
    }

    fun loadBook(page: Int, limit: Int) {
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
                        _uiState.value = TopTierUI.Failed(it)
                    }
                }
        }
    }
}