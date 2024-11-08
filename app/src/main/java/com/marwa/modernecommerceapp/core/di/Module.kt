package com.marwa.modernecommerceapp.core.di

import com.marwa.modernecommerceapp.data.datasource.local.PreferenceHelper
import com.marwa.modernecommerceapp.data.datasource.remote.interfaces.IRemoteAuthDS
import com.marwa.modernecommerceapp.data.datasource.remote.network.ApiServices.Companion.provideApiService
import com.marwa.modernecommerceapp.data.datasource.remote.network.AuthInterceptor
import com.marwa.modernecommerceapp.data.datasource.remote.network.RetrofitClient
import com.marwa.modernecommerceapp.data.datasource.remote.network.RetrofitClient.provideOkHttpClient
import com.marwa.modernecommerceapp.data.datasource.remote.network.RetrofitClient.provideRetrofit
import com.marwa.modernecommerceapp.data.datasource.remote.remote_repository.RemoteAuthDS
import com.marwa.modernecommerceapp.domain.repository.IAuthRepository
import com.marwa.modernecommerceapp.domain.repository.impl.AuthRepository
import com.marwa.modernecommerceapp.presentation.auth.login.LoginViewModel
import com.marwa.modernecommerceapp.presentation.auth.register.RegisterViewModel
import com.marwa.modernecommerceapp.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
}

val  repositoryModule = module {
    single<IAuthRepository> { AuthRepository(get(), get()) }
}

val dataSourceModule = module {
    factory { RemoteAuthDS(get()) as IRemoteAuthDS }
}

val networkModule = module {
    single<AuthInterceptor> {  AuthInterceptor(get()) }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
}

val preferenceModule = module {
    single { PreferenceHelper() }
}