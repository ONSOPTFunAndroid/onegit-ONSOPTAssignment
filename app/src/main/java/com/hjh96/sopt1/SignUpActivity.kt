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

        btn_signup_signup.setOnClickListener{
            val name = edt_signup_name.text.toString()
            val id = edt_signup_id.text.toString()
            val passwd = edt_signup_passwd.text.toString()

            if(name.isNullOrEmpty() || id.isNullOrEmpty() || passwd.isNullOrEmpty())
                Toast.makeText(this,"빈칸이 있습니다.", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this,"회원가입 완료",Toast.LENGTH_SHORT).show()

            val intent = Intent()
            intent.putExtra("signup_id", id)
            intent.putExtra("signup_passwd", passwd)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}