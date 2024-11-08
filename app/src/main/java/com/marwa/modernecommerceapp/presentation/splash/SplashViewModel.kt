package com.marwa.modernecommerceapp.presentation.splash

import androidx.lifecycle.ViewModel
import com.marwa.modernecommerceapp.domain.repository.IAuthRepository

class SplashViewModel(private val iAuthRepository: IAuthRepository):ViewModel() {
    fun isFirstUse():Boolean{
        return iAuthRepository.isFirstUse()
    }

    fun isLogged():Boolean{
        return iAuthRepository.isLoggedIn()
    }
}