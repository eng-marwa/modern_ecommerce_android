package com.marwa.modernecommerceapp.presentation.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.marwa.modernecommerceapp.R
import com.marwa.modernecommerceapp.databinding.FragmentGetStartedBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class GetStartedFragment : Fragment() {
    private val viewModel by viewModel<SplashViewModel>()
    private lateinit var binding: FragmentGetStartedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGetStartedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGetStarted.setOnClickListener {
            if (!viewModel.isLogged()) {
                findNavController().navigate(R.id.action_getStarted_navigation_to_login_navigation)
            } else {
                findNavController().navigate(R.id.action_getStarted_navigation_to_home_navigation)
            }
        }
    }
}