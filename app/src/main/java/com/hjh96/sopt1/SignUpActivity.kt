package com.hjh96.sopt1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // 회원가입 버튼 이벤트
        btn_signup_signup.setOnClickListener{
            val name = edt_signup_name.text.toString()
            val id = edt_signup_id.text.toString()
            val passwd = edt_signup_passwd.text.toString()

            if(name.isEmpty() || id.isEmpty() || passwd.isEmpty())
                Toast.makeText(this,"빈칸이 있습니다.", Toast.LENGTH_SHORT).show()
            else {
                Toast.makeText(this, "회원가입 완료", Toast.LENGTH_SHORT).show() // 모든 사항이 적혔으면 intent.puExtra를 사용하여 id와 passwd 전달
                val intent = Intent()
                intent.putExtra("signup_id", id)
                intent.putExtra("signup_passwd", passwd)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}