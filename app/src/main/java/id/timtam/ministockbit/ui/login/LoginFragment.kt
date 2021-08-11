package id.timtam.ministockbit.ui.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.github.ajalt.timberkt.d
import id.timtam.core.abstraction.BaseFragmentBinding
import id.timtam.ministockbit.databinding.FragmentLoginBinding

class LoginFragment : BaseFragmentBinding<FragmentLoginBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun doWhenCreated() {
        d { "FRAG: Created" }
        with(binding) {
            btnLoginGoogle.setOnClickListener { goToMain() }
            btnLoginFacebook.setOnClickListener { goToMain() }
            btnLogin.setOnClickListener { goToMain() }
            btnLoginWithFingerprint.setOnClickListener { goToMain() }
        }
    }

    override fun doWhenDisplayed() {
        d { "FRAG: Resumed/Displayed" }
    }

    private fun goToMain() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
    }

}