package com.example.truecallerassignment.viewHolder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.truecallerassignment.BR
import java.lang.UnsupportedOperationException

class RecyclerBindingVH(private val binding: ViewDataBinding):RecyclerView.ViewHolder(binding.root) {
    fun bind(position:Int,  item: Any?){
        if(item is String){
            binding.setVariable(BR.value, item.toString())
        }else { //if some other items gets added
            throw UnsupportedOperationException("View holder type is missing for the selection")
        }
    }
}