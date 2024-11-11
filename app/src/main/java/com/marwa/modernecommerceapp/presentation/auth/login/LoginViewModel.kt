package com.marwa.modernecommerceapp.presentation.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marwa.modernecommerceapp.data.datasource.remote.network.NetworkStatus
import com.marwa.modernecommerceapp.data.model.AuthResponse
import com.marwa.modernecommerceapp.domain.repository.IAuthRepository
import com.marwa.modernecommerceapp.utils.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val iAuthRepository: IAuthRepository) : ViewModel() {
    private val TAG = "LoginViewModel"
    private var _loginState = MutableStateFlow<ViewState<AuthResponse>>(ViewState.Empty())
    val loginState: StateFlow<ViewState<AuthResponse>> = _loginState
    fun login(email: String, password: String) {
        viewModelScope.launch {
            iAuthRepository.login(email, password).collect {
                it.status?.let { status ->
                    when (status) {
                        NetworkStatus.LOADING -> _loginState.value = ViewState.Loading()
                        NetworkStatus.SUCCESS -> {
                            it.data?.let {
                                iAuthRepository.saveAuthToken(it.user.token)
                                iAuthRepository.saveUserData(it.user)
                                _loginState.value = ViewState.Success(it)
                                iAuthRepository.setLoggedIn(true)
                            }

                        }

                        NetworkStatus.ERROR -> _loginState.value = ViewState.Error(it.error)

                        else -> _loginState.value = ViewState.Empty()
                    }
                }
            }
        }
    }
}