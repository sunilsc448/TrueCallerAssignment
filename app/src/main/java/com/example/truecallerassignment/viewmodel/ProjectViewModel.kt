package com.example.truecallerassignment.viewmodel

import androidx.lifecycle.*
import com.example.truecallerassignment.model.DataState
import com.example.truecallerassignment.repository.ProjectRepository
import com.example.truecallerassignment.storage.StorageLayer
import com.example.truecallerassignment.utility.ProjectConstants.Companion.LENGTH_CONSTANT_10
import com.example.truecallerassignment.utility.ProjectUtils
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

class ProjectViewModel @Inject constructor(private val repository:ProjectRepository,
                        private val storageLayer: StorageLayer, isNetwork: Boolean):ViewModel() {
    //emit the status of data
    private val loadingStateLiveData = MutableLiveData<DataState>()
    fun getLoadingStateLiveData():LiveData<DataState>{
        return loadingStateLiveData
    }

    //fetch api data and emit to the view observers
    private val projectData10thChar = MutableLiveData<String>()
    fun getProjectData10thChar():LiveData<String>{
        return projectData10thChar
    }

    private val projectDataEvery10thChar = MutableLiveData<String>()
    fun getProjectDataEvery10thChar():LiveData<String>{
        return projectDataEvery10thChar
    }

    private val projectDataWordCount = MutableLiveData<String>()
    fun getProjectDataWordCount():LiveData<String>{
        return projectDataWordCount
    }

    //list data
    private val every10thCharList = MutableLiveData<List<String>>()
    fun getEvery10thCharList():LiveData<List<String>>{
        return every10thCharList
    }

    private val wordsList = MutableLiveData<List<String>>()
    fun getWordsList():LiveData<List<String>>{
        return wordsList
    }

    var dataLoaded = false
        private set

    private var isNetworkAvailable:Boolean = isNetwork

    init {
        loadingStateLiveData.value = DataState.EMPTY
        if (isNetworkAvailable) {
            fetchProjectData()
        } else {
            setTheDataFromCache()
        }
    }

    //fetch the data from repository service
    private fun fetchProjectData(){
        if(!loadingStateLiveData.equals(DataState.LOADING) || !loadingStateLiveData.equals(DataState.LOADED)) {
            loadingStateLiveData.value = DataState.LOADING
            fetchLifeAsAnEngineerData10thCharacter()
            fetchLifeAsAnEngineerDataEvery10thCharacter()
            fetchLifeAsAnEngineerDataCountWords()
        }
    }

     fun fetchLifeAsAnEngineerData10thCharacter() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchLifeAsAnEngineerData(projectData10thChar)
        }
    }

    private fun fetchLifeAsAnEngineerDataEvery10thCharacter() {
        viewModelScope.launch(Dispatchers.IO) {
           repository.fetchLifeAsAnEngineerData(projectDataEvery10thChar)
        }
    }

    private fun fetchLifeAsAnEngineerDataCountWords() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchLifeAsAnEngineerData(projectDataWordCount)
        }
    }

    //Fetch data from File Cache
    private fun setTheDataFromCache() {
        var response:String? = null
        if(storageLayer.check()){
            response = storageLayer.readData()
        }

        if(response != null){
            projectData10thChar.postValue(response!!)
            projectDataEvery10thChar.postValue(response!!)
            projectDataWordCount.postValue(response!!)
            loadingStateLiveData.postValue(DataState.LOADED)
        }else{
            loadingStateLiveData.postValue(DataState.ERROR)
        }
    }

    //utility for progress bar visibility
    private var apiCount:Int = 0
    fun updateApiCount(response: String?){
        apiCount++
        if(apiCount == 3){
            loadingStateLiveData.value = DataState.LOADED
            dataLoaded = true
            writeToStorage(response)
        }
    }

    //Write the data to File Cache
    private fun writeToStorage(response: String?) {
        if(response != null && (!storageLayer.check() || storageLayer.readData() == null)){
            if(storageLayer.isExternalStorageAvailable() && !storageLayer.isExternalStorageReadOnly()) {
                storageLayer.writeData(response)
            }
        }
    }

    //network status
    fun updateNetworkStatus(networkAvailable: Boolean?) {
        networkAvailable?.let {
            isNetworkAvailable = it
        }
    }

    //reflect to change in the data
    fun dataWordCountChanged(response: String?) {
        if(response == null)return
        wordsList.postValue(ProjectUtils.getUniqueWords(response).toList())
    }

    fun dataEvery10thCharChanged(response: String?) {
        if(response == null)return
        val list = mutableListOf<String>()
        var i = LENGTH_CONSTANT_10 - 1
        while (i < response.length){
            if(!response[i].isWhitespace()) {
                list.add(response[i].toString())
            }
            i += LENGTH_CONSTANT_10
        }
        every10thCharList.postValue(list)
    }
}