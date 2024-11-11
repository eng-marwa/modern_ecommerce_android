package com.marwa.modernecommerceapp.data.datasource.remote.network

import com.marwa.modernecommerceapp.data.model.AuthResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServices {
    companion object {
        fun provideApiService(retrofit: Retrofit): ApiServices {
            return retrofit.create(ApiServices::class.java)
        }
    }

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<AuthResponse>

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("phone") phone: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<AuthResponse>
}