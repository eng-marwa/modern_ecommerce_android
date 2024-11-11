package com.marwa.modernecommerceapp.domain.repository

import com.marwa.modernecommerceapp.data.datasource.remote.network.NetworkResource
import com.marwa.modernecommerceapp.data.model.AuthResponse
import com.marwa.modernecommerceapp.data.model.UserData
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    suspend fun login(email: String, password: String):Flow<NetworkResource<AuthResponse>>
    suspend fun register(name: String, phone: String, email: String, password: String): Flow<NetworkResource<AuthResponse>>

    fun saveAuthToken(token: String)
    fun getAuthToken(): String

    fun isLoggedIn(): Boolean
    fun setLoggedIn(isLoggedIn: Boolean)

    fun isFirstUse(): Boolean
    fun setFirstUse(isFirstUse: Boolean)
    fun saveUserData(user: UserData)
}