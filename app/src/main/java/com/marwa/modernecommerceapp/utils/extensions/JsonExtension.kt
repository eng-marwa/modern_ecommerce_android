package com.marwa.modernecommerceapp.utils.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T : Any> T?.toJson() = this?.let { Gson().toJson(it, T::class.java) }
inline fun <reified T> String?.fromJson(): T? = this?.let {
    val type = object : TypeToken<T>() {}.type
    Gson().fromJson(this, type)

}