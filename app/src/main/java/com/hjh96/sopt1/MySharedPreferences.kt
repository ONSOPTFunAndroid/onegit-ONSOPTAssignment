package com.hjh96.sopt1

import android.content.Context
import android.content.SharedPreferences

object MySharedPreferences {
    private val MY_ACCOUNT : String = "account"

    fun setSignup_Id(context: Context, input: String) {
        val prefs : SharedPreferences = context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = prefs.edit() // SharedPreferences 개체의 값을 수정하는 데 사용되는 인터페이스
        editor.putString("id", input) // id라는 key 값으로 input이라는 String 저장
        editor.apply() // editor.commit()도 됨
        // commit = 동기화, apply = 비동기화
    }

    fun getSignup_Id(context: Context): String {
        val prefs : SharedPreferences = context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        return prefs.getString("id", "").toString() // id라는 key 값을 가진 string 가져오기, default = ""
    }

    fun setSignup_Pass(context: Context, input: String) {
        val prefs : SharedPreferences = context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = prefs.edit()
        editor.putString("passwd", input)
        editor.apply()
    }

    fun getSignup_Pass(context: Context): String {
        val prefs : SharedPreferences = context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        return prefs.getString("passwd", "").toString()
    }

    fun setAutologin(context: Context, input: Boolean) {
        val prefs : SharedPreferences = context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = prefs.edit()
        editor.putBoolean("autologin", input)
        editor.apply()
    }

    fun getAutologin(context: Context): Boolean {
        val prefs : SharedPreferences = context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        return prefs.getBoolean("autologin", false)
    }
    fun clearUser(context: Context) {
        val prefs : SharedPreferences = context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = prefs.edit()
        editor.clear()
        editor.apply()
    }

}