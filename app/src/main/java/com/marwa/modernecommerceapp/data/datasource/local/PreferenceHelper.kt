package com.marwa.modernecommerceapp.data.datasource.local

import android.content.SharedPreferences
import com.marwa.modernecommerceapp.data.model.UserData
import com.marwa.modernecommerceapp.utils.extensions.fromJson
import com.marwa.modernecommerceapp.utils.extensions.toJson

class PreferenceHelper(private val sharedPreferences: SharedPreferences) {


    var language: String
        get() = sharedPreferences.getString("language", "en") ?: "en"
        set(value) {
            sharedPreferences.edit().putString("language", value).apply()
        }


    var token: String
        get() = sharedPreferences.getString("token", "") ?: ""
        set(value) {
            sharedPreferences.edit().putString("token", value).apply()
        }
    var isLoggedIn: Boolean
        get() = sharedPreferences.getBoolean("isLoggedIn", false)
        set(value) {
            sharedPreferences.edit().putBoolean("isLoggedIn", value).apply()
        }

    var firstUse: Boolean
        get() = sharedPreferences.getBoolean("firstUse", true)
        set(value) {
            sharedPreferences.edit().putBoolean("firstUse", value).apply()
        }


    var userData: UserData?
        get() = sharedPreferences.getString("userData", "")?.fromJson()
        set(value) {
            sharedPreferences.edit().putString("userData", value.toJson()).apply()
        }
}