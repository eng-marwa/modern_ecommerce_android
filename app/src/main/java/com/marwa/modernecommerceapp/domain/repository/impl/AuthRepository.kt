package com.marwa.modernecommerceapp.domain.repository.impl

import com.marwa.modernecommerceapp.data.datasource.local.PreferenceHelper
import com.marwa.modernecommerceapp.data.datasource.remote.interfaces.IRemoteAuthDS
import com.marwa.modernecommerceapp.data.datasource.remote.network.NetworkResource
import com.marwa.modernecommerceapp.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepository(
    private val preferenceHelper: PreferenceHelper,
    private val iRemoteAuthDS: IRemoteAuthDS
) : IAuthRepository {
    override suspend fun login(email: String, password: String): Flow<NetworkResource<Any>> {
        return iRemoteAuthDS.login(email, password)
    }

    override suspend fun register(
        name: String,
        phone: String,
        email: String,
        password: String
    ): Flow<NetworkResource<Any>> {
        return iRemoteAuthDS.register(name,phone, email, password)
    }

    override fun saveAuthToken(token: String) {
        preferenceHelper.token = token
    }

    override fun getAuthToken(): String {
        return preferenceHelper.token
    }

    override fun isLoggedIn(): Boolean {
        return preferenceHelper.isLoggedIn
    }

    override fun setLoggedIn(isLoggedIn: Boolean) {
        preferenceHelper.isLoggedIn = isLoggedIn
    }

    override fun isFirstUse(): Boolean {
      return  preferenceHelper.firstUse
    }

    override fun setFirstUse(isFirstUse: Boolean) {
      preferenceHelper.firstUse = isFirstUse
    }

}