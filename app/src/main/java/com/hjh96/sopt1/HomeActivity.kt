package com.hjh96.sopt1;

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        txt_home_id.text = MySharedPreferences.getSignup_Id(this)
        txt_home_passwd.text = MySharedPreferences.getSignup_Pass(this)

        // 로그아웃 버튼 이벤트
        btn_home_logout.setOnClickListener {
            MySharedPreferences.setAutologin(this,false)
            finish()
        }

        // 종료 버튼 이벤트
        btn_home_exit.setOnClickListener {
            ActivityCompat.finishAffinity(this)
            System.exit(0)
        }
    }
}

