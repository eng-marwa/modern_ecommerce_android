package com.marwa.modernecommerceapp.data.datasource.remote.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

open class ApiProvider {
    fun <T> apiRequest(call: suspend () -> Response<T>): Flow<NetworkResource<T>> {
        return flow {
            emit(NetworkResource(status = NetworkStatus.LOADING))
            val response = call.invoke()
            if (response.isSuccessful) {
                emit(NetworkResource(status = NetworkStatus.SUCCESS, data = response.body()))
            } else {
                emit(
                    NetworkResource(
                        status = NetworkStatus.ERROR,
                        error = BaseException(response.code(), response.errorBody().toString())
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
    }
}