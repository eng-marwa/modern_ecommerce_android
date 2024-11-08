package com.marwa.modernecommerceapp.presentation.auth.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marwa.modernecommerceapp.domain.repository.IAuthRepository
import kotlinx.coroutines.launch

class RegisterViewModel (private val iAuthRepository: IAuthRepository) : ViewModel() {
    private  val TAG = "LoginViewModel"
    fun register(name: String, phone: String, email: String, password: String) {
        Log.d(TAG, "login: ")
        viewModelScope.launch {
            iAuthRepository.register(name, phone,email, password).collect{
                Log.d(TAG, "login: $it")
            }
        }
    }
}