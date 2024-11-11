package com.marwa.modernecommerceapp.data.model

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("message")  val message: String,
    @SerializedName("data")  val user: UserData,
    @SerializedName("status")  val status: Boolean
)


data class UserData(
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("email") val email: String,
    @SerializedName("id") val id: String,
    @SerializedName("token") val token: String,
    @SerializedName("image") val image: String,
    @SerializedName("points") val points: String,
    @SerializedName("credit") val credit: String
)