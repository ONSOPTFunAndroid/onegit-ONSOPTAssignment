# ON SOPT 27th Android Assignment



## Seminar 1

###### 제출 일자 : 2020.10.30

### 실행 화면

![alt text](https://github.com/ONSOPTFunAndroid/onegit-ONSOPTAssignment/blob/master/picture/SOPT_Assignment1.gif)



### 코드 설명

bulb: **startActivityForResult**

> MainActivity.kt

1. 회원가입 버튼을 클릭했을 때, MainActivity에서 SignUpActivity로 이동.

2. SignUpActivity의 종료와 동시에 데이터를 받아오기 위해 startActivityForResult()를 사용.
3. 기존에 startActivity()로 호출하던 것을 startActivityForResult()로 하출하면서 인수를 하나 더 추가.
4. 이 인수는 0이상의 integer 값으로 추후 onActivityResult() 메소드에도 동일한 값이 전달되며 이를 통해 하나의 onActivityResult()메소드에서 여러 개의 startActivityForResult()를 구분할 수 있음.
5.  해당 코드에서는 인수를 companion object를 사용하여 정적 변수로 초기화.

```Kotlin
// 회원가입 텍스트뷰 이벤트
txt_main_signUp.setOnClickListener {
    val intent = Intent(this, SignUpActivity::class.java)
    startActivityForResult(intent, SIGNUP_ACTIVITY_REQUEST_CODE)
}

companion object { // companion object를 사용하면 자바에서 정적 변수/메서드를 사용했던 것과 동일하게 사용할 수 있다.
        const val SIGNUP_ACTIVITY_REQUEST_CODE = 0
}
```

</br>

> SignUpActivity.kt

1. 회원가입 버튼을 눌렀을 때, EditTextView에 값이 전부 있는 경우 "회원가입 완료" 라는 ToastMessage를 띄우고 그 외에는 "빈칸이 있습니다." 라는 ToastMessage를 띄움.

2. 모든 값이 EditTextView에 전부 입력되면 intent.putExtra() 메소드를 호출하여 key에 value를 저장하고 setResult() 메소드를 호출하여 결과 코드와 intent를 세팅. 결과 코드에서 RESULT_OK와 RESULT_CANCEL 모두 Activity의 멤버 변수임. (이때 requestCode(요청 코드)는 따로 세팅해주지 않아도 MainActivity로 함께 전달됨.)

3.  finish()로 현재 액티비티를 종료하면 MainActivity로 돌아옴.

```Kotlin
		// 회원가입 버튼 이벤트
        btn_signup_signup.setOnClickListener{
            val name = edt_signup_name.text.toString()
            val id = edt_signup_id.text.toString()
            val passwd = edt_signup_passwd.text.toString()

            if(name.isEmpty() || id.isEmpty() || passwd.isEmpty())
                Toast.makeText(this,"빈칸이 있습니다.", Toast.LENGTH_SHORT).show()
            else {
                Toast.makeText(this, "회원가입 완료", Toast.LENGTH_SHORT).show() 
                // 모든 사항이 적혔으면 intent.puExtra를 사용하여 id와 passwd 전달
                val intent = Intent()
                intent.putExtra("signup_id", id)
                intent.putExtra("signup_passwd", passwd)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
```

</br>

> MainActivity.kt

1. MainActivity로 돌아오면서 onActivityResult() 메소드가 실행됨.

2. SIGNUP_ACTIVITY_REQUEST_CODE를 통해 startActivityForResult()를 구분하고 SignUpActivity에서 전달한 resultCode가 Activity.RESULT_OK일 경우 동작함.
3. getStringExtra() 메소드에 key값을 대입하여 value를 받아옴. 여기서 value는 SignUpActivity에서 저장한 데이터임.
4. 데이터를 받아오자마자 SharedPreferences에 저장한 후 MainActivity의 id와 password EditText에 값을 저장함.

```Kotlin
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
```

</br>

bulb: **SharedPreferences**

> MySharedPreferences.kt

1. 간단한 설정 값이나 문자열 같은 데이터들을 DB에 저장하기 부담스럽고 애매한 경우에 안드로이드에서 기본적으로 제공하는 SharedPreferences()를 사용하여 데이터를 관리할 수 있음.

2. App의 개별 데이터 저장소에 xml파일로 저장하여 앱을 삭제하면 데이터도 삭제됨.

3. 데이터의 형태 : key/value 형태

4. 자세한 파일 위치 : Device File Explorer에서 data/data/(package_name)/shared_prefs/(sharedPrefFile_name).xml

5. SharedPreferences 객체 생성

   getSharedPreferences()를 호출하여 생성, 인자로는 xml파일의 이름과 mode가 필요함. 해당 코드에서는 "account", Context.MODE_PRIVATE로 설정.

6. SharedPreferences 데이터 저장

   SharedPreferences .Editor를 사용. 해당 코드에서는 prefs.edit()으로 Editor 객체를 가져옴. 

   Editor.putString(), Editor.putInt() 등의 메소드를 통해 데이터 저장 가능. 첫번째 인자  key, 두번째 인자 value.

   put()을 했다고 데이터가 바로 xml파일에 저장되는 것은 아님. 데이터를 파일에 저장하려면 Editor.apply()를 호출.

   Editor.apply() : 비동기적으로(asynchronously) 동작하기 때문에 처리중인 Thread가 Blocking되지 않음.

   Editor.commit() : 동기적으로(synchronously) 동작하기 때문에 처리중인 Thread가 blocking될 수 있음.

   때문에 저장될 때까지 기다릴 필요가 없다면 apply()를 사용하는 것이 좋음.

7. SharedPreferences 데이터 읽기

   SharedPreferences.getString(), SharedPreferences.getInt() 등의 메소드를 사용하여 데이터 읽기 가능. 첫번째 인자 key, 두번째 인자 default value (key에 대한 데이터가 없을 때 리턴되는 값).

8. SharedPreferences 데이터 삭제

   Editor.clear() : 데이터 삭제

   Editor.apply() : 해당 내용 저장

```Kotlin
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
```

</br>

> MainActivity.kt

1. 로그인 버튼을 클릭했을 때, 입력한 id/password == 회원가입된 id/password 이면 HomeActivity 실행, 아이디 비번 입력 안하면 로그인 안됨.

2. HomeActivity 실행 직전에 자동로그인 체크박스에 체크가 되어 있으면 MySharedPreferences.setAutologin(this,true), 아니면 false를 저장
3. 재부팅 시 MySharedPreferences.getAutologin(this)이 true면 HomeActivity로 이동, 아니면 MainActivity 화면 출력

```Kotlin
if(MySharedPreferences.getAutologin(this)) { // 자동로그인이 표시되어 있으면 바로 HomeActivity로 화면 전환
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
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
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "아이디와 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
```

</br>

> HomeActivity.kt

1. 로그아웃 버튼

   로그아웃 버튼을 누르면 자동로그인 설정을 false로 수정 후 finish()하여 MainActivity로 돌아감.

```Kotlin
// 로그아웃 버튼 이벤트
        btn_home_logout.setOnClickListener {
            MySharedPreferences.setAutologin(this,false)
            finish()
        }
```

</br>

2. 종료 버튼

   종료 버튼을 누르면 ActivityCompat.finishAffinity(this)를 통해 액티비티를 종료한 후 System.exit(0) 명령으로 프로세스를 종료함. 

   App을 실행시키면 자동로그인이 됨.

```Kotlin
// 종료 버튼 이벤트
        btn_home_exit.setOnClickListener {
            ActivityCompat.finishAffinity(this) // 액티비티 종료
            System.exit(0) // 프로세스 종료
        }
```

</br></br>



## Seminar 2

###### 제출 일자 : 2020.10.30

### 실행 화면

![alt text](https://github.com/ONSOPTFunAndroid/onegit-ONSOPTAssignment/blob/master/picture/SOPT_Assignment_2.gif)



### 코드 설명





