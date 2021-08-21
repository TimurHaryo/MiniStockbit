package id.timtam.core.lifecycle

import androidx.lifecycle.*

internal class FragmentLifecycleAware(private val lifecycle: Lifecycle) : LifecycleObserver {

    private var hasBeenFetched: Boolean = false

    private var onInitView: (() -> Unit)? = null
    private var onFetchData: (() -> Unit)? = null

    fun setOnInitView(action: () -> Unit) {
        this.onInitView = action
    }

    fun setOnFetchData(action: () -> Unit) {
        this.onFetchData = action
    }

    fun registerLifecycleAware() {
        this.lifecycle.addObserver(this)
    }

    fun unregisterLifecycleAware() {
        this.lifecycle.removeObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun initView() {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            onInitView?.invoke()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun fetchData() {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED) && !hasBeenFetched) {
            onFetchData?.invoke()
            hasBeenFetched = true
        }
    }
}