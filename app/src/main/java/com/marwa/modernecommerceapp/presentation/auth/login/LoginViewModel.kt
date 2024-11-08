package com.marwa.modernecommerceapp.presentation.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marwa.modernecommerceapp.domain.repository.IAuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val iAuthRepository: IAuthRepository) : ViewModel() {
    private  val TAG = "LoginViewModel"
    fun login(email: String, password: String) {
        Log.d(TAG, "login: ")
        viewModelScope.launch {
            iAuthRepository.login(email, password).collect{
                Log.d(TAG, "login: $it")
            }
        }
    }
}