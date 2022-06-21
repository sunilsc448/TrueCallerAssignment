package com.example.truecallerassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.truecallerassignment.utility.ProjectConstants.Companion.LENGTH_CONSTANT_10
import com.example.truecallerassignment.utility.ProjectConstants.Companion.LENGTH_CONSTANT_500
import com.example.truecallerassignment.utility.ProjectUtils
import com.example.truecallerassignment.utility.SingleLiveEvent
import javax.inject.Inject

class ItemsViewModel @Inject constructor():ViewModel() {
    //emit the filtered changes to bind views
    private val tenthChar = MutableLiveData<String>()
    fun getTenthChar(): LiveData<String> {
        return tenthChar
    }

    private val everyTenthCharOverview = MutableLiveData<String>()
    fun getEveryTenthCharOverview(): LiveData<String> {
        return everyTenthCharOverview
    }

    private val wordsCountOverview = MutableLiveData<String>()
    fun getWordsCountOverview(): LiveData<String> {
        return wordsCountOverview
    }

    //click event listener
    private val viewMoreClicked:MutableLiveData<String> = SingleLiveEvent()
    fun getViewMoreClicked():LiveData<String>{
        return viewMoreClicked
    }

    //reflect to change in the data
    fun data10thCharChanged(response:String?) {
        val char = ProjectUtils.get10thChar(response)
        tenthChar.postValue(char.toString())
    }

    fun dataEvery10thCharChanged(response: String?) {
        val result = ProjectUtils.getEvery10thCharBrief(response)
        if(result.length >= LENGTH_CONSTANT_500){
            everyTenthCharOverview.postValue(result.substring(0, LENGTH_CONSTANT_500))
        }else{
            everyTenthCharOverview.postValue(result)
        }
    }

    fun dataWordCountChanged(response: String?) {
        if(response != null) {
            val uniqueWords = ProjectUtils.getUniqueWords(response)
            val resultStr =  ProjectUtils.getWordsBriefString(uniqueWords)
            wordsCountOverview.postValue(resultStr)
        }
    }

    fun viewMoreClicked(type:String) {
        viewMoreClicked.postValue(type)
    }
}