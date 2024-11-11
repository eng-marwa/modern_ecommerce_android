package com.marwa.modernecommerceapp.presentation.auth.register

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
import com.marwa.modernecommerceapp.databinding.FragmentRegisterBinding
import com.marwa.modernecommerceapp.utils.ViewState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModel<RegisterViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeRegisterState()
        binding.btnRegister.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val confirmPasswrod = binding.confirmPasswordEditText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && phone.isNotEmpty() && password == confirmPasswrod) {
                // call register function from viewmodel
                viewModel.register(name, phone, email, password)
            }
        }

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_register_navigation_to_login_navigation)
        }
    }

    private fun observeRegisterState() {
        lifecycleScope.launch {
            viewModel.registerState.collect {
                when (it) {
                    is ViewState.Loading -> {
                        binding.loader.visibility = View.VISIBLE
                    }

                    is ViewState.Success -> onRegisterSuccess(it.data)
                    is ViewState.Error -> onRegisterFailed(it.error)
                    else -> {
                        binding.loader.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun onRegisterFailed(error: BaseException?) {
        binding.loader.visibility = View.GONE
        Toast.makeText(requireContext(), error?.message ?: "", Toast.LENGTH_SHORT).show()
    }

    private fun onRegisterSuccess(data: AuthResponse?) {
        binding.loader.visibility = View.GONE
        Toast.makeText(requireContext(), data?.message ?: "", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_register_navigation_to_home_navigation)

    }
}