package com.marwa.modernecommerceapp.data.datasource.remote.interfaces

import com.marwa.modernecommerceapp.data.datasource.remote.network.NetworkResource
import com.marwa.modernecommerceapp.data.model.AuthResponse
import kotlinx.coroutines.flow.Flow

interface IRemoteAuthDS {
    suspend fun login(email: String, password: String): Flow<NetworkResource<AuthResponse>>

    suspend fun register(name: String, phone: String, email: String, password: String): Flow<NetworkResource<AuthResponse>>
}