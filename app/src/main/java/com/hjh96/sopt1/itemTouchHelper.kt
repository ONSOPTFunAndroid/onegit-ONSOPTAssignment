package com.hjh96.sopt1

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

fun itemTouchHelper(adapter : ProfileAdapter) : ItemTouchHelper{
    val helper = ItemTouchHelper(object :
        ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
            ItemTouchHelper.START
        ) {
        override fun onMove( // 움직이는 것
            recyclerView: RecyclerView,
            from: RecyclerView.ViewHolder,
            to: RecyclerView.ViewHolder
        ): Boolean {
            val fromPosition = from.adapterPosition
            val toPosition = to.adapterPosition
            adapter.notifyItemMoved(fromPosition, toPosition)
            return false
        }
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) { // 삭제
            adapter.data.removeAt(viewHolder.adapterPosition)
            adapter.notifyItemRemoved(viewHolder.adapterPosition)
        }
    })
    return helper
}