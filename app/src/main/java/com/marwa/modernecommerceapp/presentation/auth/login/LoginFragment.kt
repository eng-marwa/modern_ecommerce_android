package com.marwa.modernecommerceapp.presentation.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.marwa.modernecommerceapp.R
import com.marwa.modernecommerceapp.data.datasource.remote.network.BaseException
import com.marwa.modernecommerceapp.data.model.AuthResponse
import com.marwa.modernecommerceapp.databinding.FragmentLoginBinding
import com.marwa.modernecommerceapp.utils.ViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModel<LoginViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLoginState()
        binding.btnLogin.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // call login function from viewmodel
                viewModel.login(email, password)
            }
        }
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_login_navigation_to_register_navigation)
        }
    }

    private fun observeLoginState() {
        lifecycleScope.launch {
            viewModel.loginState.collect {
                when (it) {
                    is ViewState.Loading -> {
                        binding.loader.visibility = View.VISIBLE
                    }

                    is ViewState.Success -> onLoginSuccess(it.data)
                    is ViewState.Error -> onLoginFailed(it.error)
                    else -> {
                        binding.loader.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun onLoginFailed(error: BaseException?) {
        binding.loader.visibility = View.GONE
        Toast.makeText(requireContext(), error?.message ?: "", Toast.LENGTH_SHORT).show()
    }

    private fun onLoginSuccess(data: AuthResponse?) {
        binding.loader.visibility = View.GONE
        findNavController().navigate(R.id.action_login_navigation_to_home_navigation)
        Toast.makeText(requireContext(), data?.message ?: "", Toast.LENGTH_SHORT).show()
    }

}