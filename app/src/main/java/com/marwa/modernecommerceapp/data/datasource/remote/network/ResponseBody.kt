package com.marwa.modernecommerceapp.data.datasource.remote.network

data class ResponseBody<T>(
    val status: Boolean,
    val data: T? = null,
    val message: String
)