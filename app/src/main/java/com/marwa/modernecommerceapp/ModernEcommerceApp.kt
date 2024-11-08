package com.marwa.modernecommerceapp

import android.app.Application
import com.marwa.modernecommerceapp.core.di.dataSourceModule
import com.marwa.modernecommerceapp.core.di.networkModule
import com.marwa.modernecommerceapp.core.di.preferenceModule
import com.marwa.modernecommerceapp.core.di.repositoryModule
import com.marwa.modernecommerceapp.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class ModernEcommerceApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ModernEcommerceApp)
            androidLogger()
            modules(viewModelModule, repositoryModule, networkModule , dataSourceModule, preferenceModule)
        }
    }
}