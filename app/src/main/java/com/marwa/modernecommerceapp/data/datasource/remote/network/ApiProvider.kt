package com.marwa.modernecommerceapp.data.datasource.remote.network

import android.util.Log
import com.google.gson.Gson
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
                val responseBodyJson = Gson().toJson(response.body())
                val responseBody = Gson().fromJson(responseBodyJson, ResponseBody::class.java)
                println("responseBody: ${responseBody.status}")
                if (!responseBody.status) {
                    emit(
                        NetworkResource(
                            status = NetworkStatus.ERROR,
                            error = BaseException(response.code(), responseBody.message)
                        )
                    )
                } else {
                    emit(NetworkResource(status = NetworkStatus.SUCCESS, data = response.body()))
                }
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