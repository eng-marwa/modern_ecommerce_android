package com.marwa.modernecommerceapp.utils

import com.marwa.modernecommerceapp.data.datasource.remote.network.BaseException

open class ViewState<T>(open val data: T? = null) {
    class Empty<T> : ViewState<T>()
    class Loading<T> : ViewState<T>()
    class Success<T>(data: T) : ViewState<T>(data)
    class Error<T>(val error: BaseException? = null) : ViewState<T>()
}