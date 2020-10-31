package com.hjh96.sopt1

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// 전해받은 레이아웃(activity_profile.xml)의 뷰에 있는 텍스트필드 id를 요소로 받아 데이터를 넣어줌 (리사이클러뷰 상속)
class ProfileViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val title : TextView = itemView.findViewById(R.id.txt_profile_title)
    private val subTitle = itemView.findViewById<TextView>(R.id.txt_profile_subtiltle)

    fun onBind(data : ProfileData){ //Adapter에서 함수 호출, 실질적으로 데이터를 요소들에 저장하는 함수
        title.text = data.title
        subTitle.text = data.subTitle
    }
}