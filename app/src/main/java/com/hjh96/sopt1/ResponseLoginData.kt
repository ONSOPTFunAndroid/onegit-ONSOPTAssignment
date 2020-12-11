package com.hjh96.sopt1

data class ResponseLoginData(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : LoginData
) {
    data class LoginData(
        val email : String,
        val password : String,
        val userName : String
    )
}