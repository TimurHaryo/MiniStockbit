package id.timtam.core.abstraction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding

abstract class BaseFragmentBinding<T: ViewBinding> : Fragment() {

    private var _binding: T? = null

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
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            doWhenCreated()
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            doWhenDisplayed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        toBeCleared()
        _binding = null
    }

    protected abstract fun doWhenCreated()

    protected open fun doWhenDisplayed() {  }

    protected open fun toBeCleared() {  }

}