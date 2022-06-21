package com.example.truecallerassignment.adapter

import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.truecallerassignment.listener.IClickListener
import com.example.truecallerassignment.R
import com.example.truecallerassignment.utility.ViewHolderFactory
import java.lang.UnsupportedOperationException

class RecyclerViewAdapter(var mList:List<Any>?, val listener: IClickListener?, val viewModel: ViewModel?):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        if(mList == null)return -1
        return when (mList!![position]) {
            is String -> {
                R.layout.project_data_item
            }
            else->{
                throw UnsupportedOperationException("Layout is missing for the selection")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vh = ViewHolderFactory.getViewHolder(viewType, parent, listener, viewModel)
        return vh
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        ViewHolderFactory.bindViewHolder(position, getItemViewType(position), holder, mList?.get(position))
    }

    override fun getItemCount():Int{
        return mList?.size ?: 0
    }

    fun updateData(list:List<Any>?){
        mList = list
        notifyDataSetChanged()
    }
}