package com.example.truecallerassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.truecallerassignment.ApplicationClass
import com.example.truecallerassignment.network.ConnectivityLiveData

open class BaseActivity : AppCompatActivity() {
    protected lateinit var connectivityLiveData: ConnectivityLiveData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityLiveData = ConnectivityLiveData(ApplicationClass.application)
    }
}