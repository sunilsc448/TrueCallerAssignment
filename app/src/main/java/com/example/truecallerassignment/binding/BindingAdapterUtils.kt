package com.example.truecallerassignment.binding

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.truecallerassignment.R
import com.example.truecallerassignment.adapter.RecyclerViewAdapter
import com.example.truecallerassignment.utility.ProjectConstants.Companion.EVERY_TENTH_CHAR
import com.example.truecallerassignment.utility.ProjectConstants.Companion.VIEW_MORE
import com.example.truecallerassignment.utility.ProjectConstants.Companion.WORDS_COUNT
import com.example.truecallerassignment.viewmodel.ItemsViewModel
import java.util.*

object BindingAdapterUtils {
    @BindingAdapter("setTenthChar")
    @JvmStatic
    fun setTenthChar(textView: TextView, response:String?){
        if(response != null) {
            textView.text = String.format(textView.resources.getString(R.string.tenth_character_is), response)
        }else{
            textView.text = textView.resources.getString(R.string.fetching_10th_char_please_wait)
        }
    }

    @BindingAdapter(value = ["viewModel", "everyTenthChar"])
    @JvmStatic
    fun everyTenthChar(textView: TextView, viewModel: ItemsViewModel, response:String?){
        if(response != null) {
            textView.setText(getSpannabledText(String.format(textView.resources.getString(R.string.every_tenth_character_is), response), VIEW_MORE, viewModel, EVERY_TENTH_CHAR))
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }else{
            textView.text = textView.resources.getString(R.string.fetching_list_every_10th_char_please_wait)
        }
    }

    @BindingAdapter(value = ["viewModel", "setWordsCount"])
    @JvmStatic
    fun setWordsCount(textView: TextView,viewModel: ItemsViewModel, response:String?){
        if(response != null) {
            textView.setText(getSpannabledText(String.format(textView.resources.getString(R.string.words_count_is), response), VIEW_MORE, viewModel, WORDS_COUNT))
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }else{
            textView.text = textView.resources.getString(R.string.fetching_all_unique_words)
        }
    }

    //view more styling
    private fun getSpannabledText(contentStr: String, linkStr: String, viewModel: ItemsViewModel, type:String): SpannableString? {
        val spannableTxt = SpannableString(contentStr)
        if (!TextUtils.isEmpty(linkStr)) {
            val startIndex = contentStr.indexOf(linkStr)
            val endIndex = startIndex + linkStr.length
            spannableTxt.setSpan(
                ForegroundColorSpan(Color.parseColor("#00baf2")),
                startIndex,
                endIndex,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableTxt.setSpan(object : ClickableSpan(){
                override fun onClick(widget: View) {
                    viewModel.viewMoreClicked(type)
                }
            }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return spannableTxt
    }

    @BindingAdapter(value = ["viewModel","data"])
    @JvmStatic
    fun setItemsAdapter(recyclerView: RecyclerView, viewModel: ViewModel?, data:List<String>?) {
        if (data != null) {
            var adapter = recyclerView.adapter
            if (adapter == null){
                adapter = RecyclerViewAdapter(data, null, viewModel)
                recyclerView.adapter = adapter
            }else{
                (adapter as RecyclerViewAdapter).updateData(data)
                recyclerView.scrollToPosition(0)
            }
        }
    }
}