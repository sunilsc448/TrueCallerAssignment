package com.example.truecallerassignment.utility

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.truecallerassignment.listener.IClickListener
import com.example.truecallerassignment.R
import com.example.truecallerassignment.viewHolder.RecyclerBindingVH
import com.example.truecallerassignment.BR

object ViewHolderFactory {
    fun getViewHolder(resourceId: Int, parent: ViewGroup, listener: IClickListener?, viewModel: ViewModel?): RecyclerView.ViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding: ViewDataBinding = DataBindingUtil.inflate(inflater, resourceId, parent, false)
        dataBinding.setVariable(BR.listener, listener)
        dataBinding.setVariable(BR.viewmodel, viewModel)
        return RecyclerBindingVH(dataBinding)
    }
    fun bindViewHolder(pos:Int, resourceId:Int, holder:RecyclerView.ViewHolder, obj:Any?){
        if(obj == null)return
        when(resourceId){
            R.layout.project_data_item -> {
                (holder as RecyclerBindingVH).bind(pos, obj)
            }
           // In case more items gets added
        }
    }
}