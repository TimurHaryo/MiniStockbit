package id.timtam.core.abstraction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T> : ViewModel() {

    protected val _uiState: MutableLiveData<T> = MutableLiveData()

    val uiState: LiveData<T>
        get() = _uiState

}