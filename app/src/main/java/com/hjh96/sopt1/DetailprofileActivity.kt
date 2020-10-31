package com.hjh96.sopt1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail_profile.*

class DetailProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_profile)

//        txt_detail_title.text = intent.getStringExtra("title")
//        txt_detail_subtitle.text = intent.getStringExtra("subTitle")
//        txt_detail_content.text = intent.getStringExtra("content")
        val profile = intent.getParcelableExtra<ProfileData>("profile")
        txt_detail_title.text = profile?.title
        txt_detail_subtitle.text = profile?.subTitle
        txt_detail_content.text = profile?.content
    }
}