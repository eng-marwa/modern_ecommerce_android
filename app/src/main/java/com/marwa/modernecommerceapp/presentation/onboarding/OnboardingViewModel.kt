package com.marwa.modernecommerceapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import com.marwa.modernecommerceapp.domain.repository.IAuthRepository

class OnboardingViewModel(private val iAuthRepository: IAuthRepository):ViewModel() {
    fun setFirstUse(isFirstUse: Boolean) {
        iAuthRepository.setFirstUse(isFirstUse)
    }

    fun isLoggedIn(): Boolean {
        return iAuthRepository.isLoggedIn()
    }

}