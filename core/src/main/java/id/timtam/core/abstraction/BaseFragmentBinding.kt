package id.timtam.core.abstraction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import id.timtam.core.lifecycle.FragmentLifecycleAware

abstract class BaseFragmentBinding<T: ViewBinding> : Fragment() {

    private var _binding: T? = null

    private var fragmentLifecycleAware: FragmentLifecycleAware? = null

    protected abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T

    protected val binding: T
        get() {
            if (_binding == null) {
                throw IllegalArgumentException("${this.javaClass.simpleName} does not initialize view binding")
            }
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)

        fragmentLifecycleAware =
            FragmentLifecycleAware(viewLifecycleOwner.lifecycle)

        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentLifecycleAware?.let {
            it.setOnInitView { doWhenCreated() }
            it.setOnFetchData { doOnceWhenDisplayed() }
            it.registerLifecycleAware()
        }
    }

    override fun onDestroyView() {
        fragmentLifecycleAware?.unregisterLifecycleAware()

        super.onDestroyView()

        toBeCleared()
        fragmentLifecycleAware = null
        _binding = null
    }

    protected abstract fun doWhenCreated()

    protected open fun doOnceWhenDisplayed() {  }

    protected open fun toBeCleared() {  }

}