package com.marwa.modernecommerceapp.data.datasource.remote.network


import com.marwa.modernecommerceapp.BuildConfig
import com.marwa.modernecommerceapp.data.datasource.local.PreferenceHelper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    //3- provide object retrofit
    //2- provide object okhttp
    //1- provide interceptors

    private val TIME_OUT = 5L


    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return  Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder().readTimeout(TIME_OUT, TimeUnit.SECONDS).writeTimeout(
            TIME_OUT, TimeUnit.SECONDS
        ).connectTimeout(TIME_OUT, TimeUnit.SECONDS).addInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }
}

class AuthInterceptor(val pref: PreferenceHelper) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        req.newBuilder().addHeader("Content-Type", "application/json")
            .addHeader("lang", pref.language)
            .addHeader(
                "Authorization", "Bearer ${pref.token}"
            )
            .method(req.method, req.body).build()

        return chain.proceed(req)

    }

}