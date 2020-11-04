package com.hjh96.sopt1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(MySharedPreferences.getAutologin(this)) { // 자동로그인이 표시되어 있으면 바로 HomeActivity로 화면 전환
            val intent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(intent)
        }

        // 회원가입 텍스트뷰 이벤트
        txt_main_signUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, SIGNUP_ACTIVITY_REQUEST_CODE)
        }

        // 로그인 버튼 이벤트
        btn_main_login.setOnClickListener {
            if(edt_main_inputId.text.toString() == MySharedPreferences.getSignup_Id(this) && // 입력한 id와 pw, 그리고 회원가입된 id와 pw가 같으면 HomeActivity 실행, 아이디 비번 입력 안하면 로그인 안됨
                edt_main_inputPasswd.text.toString() == MySharedPreferences.getSignup_Pass(this) &&
                edt_main_inputId.text.toString() != "" && edt_main_inputPasswd.text.toString() != ""){
                if(chk_main_autologin.isChecked) // 자동로그인 확인
                    MySharedPreferences.setAutologin(this,true)
                else
                    MySharedPreferences.setAutologin(this,false)
                val intent = Intent(this, RecyclerViewActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "아이디와 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Check that it is the SecondActivity with an OK result
        if (requestCode == SIGNUP_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                // Get String data from Intent
                MySharedPreferences.setSignup_Id(this, (data!!.getStringExtra("signup_id")).toString())
                MySharedPreferences.setSignup_Pass(this, (data!!.getStringExtra("signup_passwd")).toString())

                // Set edit text with string
                edt_main_inputId.setText(MySharedPreferences.getSignup_Id(this))
                edt_main_inputPasswd.setText(MySharedPreferences.getSignup_Pass(this))
            }
        }
    }

    companion object { // companion object를 사용하면 자바에서 정적 변수/메서드를 사용했던 것과 동일하게 사용할 수 있다. (프로젝트 일부  한정?)
        const val SIGNUP_ACTIVITY_REQUEST_CODE = 0
    }
}