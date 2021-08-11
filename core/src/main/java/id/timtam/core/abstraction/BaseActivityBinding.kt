package id.timtam.core.abstraction

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivityBinding<T: ViewBinding> : AppCompatActivity() {

    private var _binding: T? = null

    protected abstract val bindingInflater: (LayoutInflater) -> T

    protected val binding: T
        get() {
            if (_binding == null) {
                throw IllegalArgumentException("${this.javaClass.simpleName} does not initialize view binding")
            }
            return _binding!!
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        setupView()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    protected abstract fun setupView()

}