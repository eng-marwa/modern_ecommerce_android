package com.marwa.modernecommerceapp.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marwa.modernecommerceapp.data.datasource.remote.network.NetworkStatus
import com.marwa.modernecommerceapp.data.model.AuthResponse
import com.marwa.modernecommerceapp.domain.repository.IAuthRepository
import com.marwa.modernecommerceapp.utils.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(private val iAuthRepository: IAuthRepository) : ViewModel() {
    private val TAG = "LoginViewModel"

    private var _registerState = MutableStateFlow<ViewState<AuthResponse>>(ViewState.Empty())
    var registerState: StateFlow<ViewState<AuthResponse>> = _registerState
    fun register(name: String, phone: String, email: String, password: String) {
        viewModelScope.launch {
            iAuthRepository.register(name, phone, email, password).collect {
                it.status?.let { status ->
                    when (status) {
                        NetworkStatus.LOADING -> _registerState.value = ViewState.Loading()

                        NetworkStatus.SUCCESS -> {
                            it.data?.let {
                                iAuthRepository.setLoggedIn(true)
                                iAuthRepository.saveAuthToken(it.user.token)
                                iAuthRepository.saveUserData(it.user)
                                _registerState.value = ViewState.Success(it)
                            }

                        }

                        NetworkStatus.ERROR -> _registerState.value = ViewState.Error(it.error)

                        else -> _registerState.value = ViewState.Empty()
                    }

                }

            }
        }
    }
}