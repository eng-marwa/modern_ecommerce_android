package com.marwa.modernecommerceapp.data.datasource.remote.network

class NetworkResource<T>(
    val status :NetworkStatus? = NetworkStatus.IDLE,
    val data :T? = null,
    val error : BaseException? = null
) {
    fun <T> ready(): NetworkResource<T> {
        return NetworkResource()
    }

    fun <T> loading(): NetworkResource<T> {
        return NetworkResource(status= NetworkStatus.LOADING)
    }

    fun <T> success(data: T): NetworkResource<T> {
        return NetworkResource(status= NetworkStatus.SUCCESS, data = data)
    }

    fun <T> error(error: BaseException): NetworkResource<T> {
        return NetworkResource(status= NetworkStatus.ERROR, error = error)
    }
}