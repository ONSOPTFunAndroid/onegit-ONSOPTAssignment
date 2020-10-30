package com.hjh96.sopt1

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProfileAdapter(private val context : Context) : RecyclerView.Adapter<ProfileViewHolder>()  {

    var data = mutableListOf<ProfileData>() // ProfileData를 가져옴

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_profile,parent,false)
        return ProfileViewHolder(view)
    }

    override fun getItemCount(): Int = data.size // 데이터 사이즈 == 아이템 개수
//    override fun getItemCount(): Int {
//        return data.size
//    } 같은 코드

    // 실질적으로 데이터를 뷰에 저장
    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}