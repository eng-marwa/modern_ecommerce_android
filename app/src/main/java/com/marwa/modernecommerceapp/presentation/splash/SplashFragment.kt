package com.marwa.modernecommerceapp.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.marwa.modernecommerceapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private val viewModel by viewModel<SplashViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (viewModel.isFirstUse()) {
                findNavController().navigate(R.id.action_splash_navigation_to_onboarding_navigation)
            } else if (!viewModel.isLogged()) {
                findNavController().navigate(R.id.action_splash_navigation_to_login_navigation)
            } else {
                findNavController().navigate(R.id.action_splash_navigation_to_home_navigation)
            }
        }, 3000)
    }

}