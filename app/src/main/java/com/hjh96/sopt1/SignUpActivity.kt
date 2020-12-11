package com.hjh96.sopt1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import com.hjh96.sopt1.RequestLoginData
import com.hjh96.sopt1.RequestSignUpData
import com.hjh96.sopt1.api.SoptServiceImpl
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // 회원가입 버튼 이벤트
        btn_signup_signup.setOnClickListener{
            val userName = edt_signup_name.text.toString()
            val email = edt_signup_id.text.toString()
            val password = edt_signup_passwd.text.toString()

            if(userName.isEmpty() || email.isEmpty() || password.isEmpty())
                Toast.makeText(this,"빈칸이 있습니다.", Toast.LENGTH_SHORT).show()
            else {
                Toast.makeText(this, "회원가입 완료", Toast.LENGTH_SHORT).show() // 모든 사항이 적혔으면 intent.puExtra를 사용하여 id와 passwd 전달
                val intent = Intent()
                intent.putExtra("signup_id", email)
                intent.putExtra("signup_passwd", password)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }

            val call : Call<ResponseSignUpData> = SoptServiceImpl.baseService.postSignUp( // Call 객체 받기, 싱글톤 객체 이용
                RequestSignUpData(email = email, password = password, userName = userName)
            )
            // 여기까지 데이터 및 통신 준비

            call.enqueue(object : Callback<ResponseSignUpData> { // 실질적으로 통신하는 부분
                override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) { // 일회성인 익명클래스 활용용
                   // 통신 실패 로직
                    Log.d("tag", t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResponseSignUpData>, // 매개변수1
                    response: Response<ResponseSignUpData> // 매개변수2
                ) {
                    response.takeIf { it.isSuccessful } // 서버에서 200~300을 주면 true, 그 외 null
                        ?.body()
                        ?.let { it -> // response.body()가 비었는지 한번 더 확인
                            // do something
                        } ?: showError(response.errorBody()) // 서버가 준 에러메시지 출력
                }
            })
        }
    }

    private fun showError(error : ResponseBody?) {
        val e = error ?: return
        val ob = JSONObject(e.string())
        Toast.makeText(this, ob.getString("message"), Toast.LENGTH_LONG).show()
    }
}