package com.hjh96.sopt1

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recyclerview.*

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var profileAdapter : ProfileAdapter // lateinit으로 초기화를 늦춤

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        profileAdapter = ProfileAdapter(this) // this = RecyclerViewActivity
        main_rcv.itemTouchHelper(profileAdapter) // itemTouchHelper 사용
        main_rcv.apply{
            adapter = profileAdapter // RecyclerView의 adapter에 profileAdapter를 세팅
            layoutManager = LinearLayoutManager(this@RecyclerViewActivity) // RecyclerView의 배치 방향을 LinearLayoutManager로 설정, default로 vertical
            MySharedPreferences.setLayout(this@RecyclerViewActivity, "0") // 0 -> Linear
        }
        // 아래 코드와 동일
//        main_rcv.adapter = profileAdapter
//        main_rcv.layoutManager = LinearLayoutManager(this)

        profileAdapter.data = mutableListOf( // Adapter의 data 리스트에 데이터 저장
            ProfileData("이름", "한재현"),
            ProfileData("나이", "25"),
            ProfileData("파트", "안드로이드"),
            ProfileData("Github", "www.github.com/wogus0333"),
            ProfileData("phone", "010-2384-3932"),
            ProfileData("sopt", "www.sopt.org"),
            ProfileData("insta", "www.instagram.com/onejh96__")
        )
        profileAdapter.notifyDataSetChanged() // Adapter에 데이터가 갱신되었다고 알려주기
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // 옵션메뉴 리소스 지정
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // 옵션 메뉴의 처리
        when(item?.itemId){
            R.id.item_menu_logout -> { // 로그아웃 버튼 이벤트
                MySharedPreferences.setAutologin(this,false)
                finish()
            }
            R.id.item_menu_exit -> { // 종료 버튼 이벤트
                ActivityCompat.finishAffinity(this)
                System.exit(0)
            }
            R.id.item_menu_linear -> {
                main_rcv.layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
            }
            R.id.item_menu_grid -> {
                main_rcv.layoutManager = GridLayoutManager(this@RecyclerViewActivity, 3)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}