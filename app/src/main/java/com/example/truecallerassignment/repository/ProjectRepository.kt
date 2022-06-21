package com.example.truecallerassignment.repository

import androidx.lifecycle.MutableLiveData
import com.example.truecallerassignment.api.ProjectApiService
import kotlinx.coroutines.*
import retrofit2.Callback
import javax.inject.Inject

class ProjectRepository @Inject constructor(private val apiService:ProjectApiService) {
     //unused - for retrofit call back interface
     fun callLifeAsAnEngineerData(callback: Callback<String>){
        val call = apiService.callLifeAsAnEngineerData()
        call.enqueue(callback)
    }

    //coroutine with context approach
    suspend fun fetchLifeAsAnEngineerData(dataBroadcaster: MutableLiveData<String>?){
        val job = withContext(Dispatchers.Default) {
            apiService.getLifeAsAnEngineerData()
        }
        if(job.isSuccessful && job.body() != null){
            val response = job.body()
            dataBroadcaster?.postValue(response!!)
        }

//         return withContext(Dispatchers.IO){
//             val response = async{
//                 apiService.getLifeAsAnEngineerData()
//             }
//             var result = response.await().body()
//             return@withContext result
//         }
    }
}