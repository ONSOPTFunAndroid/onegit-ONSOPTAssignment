package com.hjh96.sopt1.api

import com.hjh96.sopt1.RequestLoginData
import com.hjh96.sopt1.RequestSignUpData
import com.hjh96.sopt1.ResponseLoginData
import com.hjh96.sopt1.ResponseSignUpData
import retrofit2.Call
import retrofit2.http.*

interface SoptService {
    // 회원가입
    @Headers("Content-Type: application/json")
    @POST("/users/signup")
    fun postSignUp(
        @Body body: RequestSignUpData
    ) : Call<ResponseSignUpData>

    // 로그인
    @Headers("Content-Type: application/json")
    @POST("/users/signin")
    fun postLogin(
        @Body body : RequestLoginData
    ) : Call<ResponseLoginData>
}