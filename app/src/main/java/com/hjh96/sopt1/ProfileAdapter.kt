package com.hjh96.sopt1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProfileAdapter(private val context : Context) : RecyclerView.Adapter<ProfileViewHolder>()  {

    var data = mutableListOf<ProfileData>() // ProfileData를 가져옴
    var layoutItem = R.layout.item_linear_profile

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(context).inflate(layoutItem,parent,false)
        return ProfileViewHolder(view).apply {
            // item을 클릭하면 상세 화면으로 이동
            itemView.setOnClickListener {
                val curPosition : Int = adapterPosition
                val profile : ProfileData = data.get(curPosition)
                val intent = Intent(context, DetailProfileActivity::class.java)

//                intent.putExtra("title", profile.title)
//                intent.putExtra("subTitle", profile.subTitle)
//                intent.putExtra("content", profile.content)
                intent.putExtra("profile", profile)
                context.startActivity(intent)
            }
        }
    }

    fun shuffle() {
        this.data.shuffle()
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = data.size // 데이터 사이즈 == 아이템 개수
//    override fun getItemCount(): Int {
//        return data.size
//    } 같은 코드

    // 실질적으로 데이터를 뷰에 저장
    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    fun LayoutChange(layoutItem:Int){
        this.layoutItem = layoutItem
    }
}