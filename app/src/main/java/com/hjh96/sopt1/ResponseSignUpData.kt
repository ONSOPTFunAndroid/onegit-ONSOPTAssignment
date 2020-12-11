package com.hjh96.sopt1

data class ResponseSignUpData(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : SignUpData
) {
    data class SignUpData(
        val email : String,
        val password : String,
        val userName : String
    )
}