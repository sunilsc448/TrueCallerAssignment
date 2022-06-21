package com.example.truecallerassignment.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ProjectApiService {
    @GET("/2018/01/22/life-as-an-android-engineer/")
    fun callLifeAsAnEngineerData(): Call<String>

    @GET("/2018/01/22/life-as-an-android-engineer/")
    suspend fun getLifeAsAnEngineerData(): Response<String>
}