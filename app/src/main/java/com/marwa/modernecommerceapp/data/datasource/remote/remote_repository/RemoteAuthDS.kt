package com.marwa.modernecommerceapp.data.datasource.remote.remote_repository

import com.marwa.modernecommerceapp.data.datasource.remote.interfaces.IRemoteAuthDS
import com.marwa.modernecommerceapp.data.datasource.remote.network.ApiProvider
import com.marwa.modernecommerceapp.data.datasource.remote.network.ApiServices
import com.marwa.modernecommerceapp.data.datasource.remote.network.NetworkResource
import kotlinx.coroutines.flow.Flow

class RemoteAuthDS(private val apiServices: ApiServices) : IRemoteAuthDS, ApiProvider() {
    override suspend fun login(email: String, password: String): Flow<NetworkResource<Any>> {
        return apiRequest { apiServices.login(email, password) }
    }

    override suspend fun register(
        name: String,
        phone: String,
        email: String,
        password: String
    ): Flow<NetworkResource<Any>> {
       return apiRequest { apiServices.register(name, phone, email, password) }
    }
}