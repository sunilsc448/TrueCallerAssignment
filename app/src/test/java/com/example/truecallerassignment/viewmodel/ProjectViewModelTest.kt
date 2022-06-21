package com.example.truecallerassignment.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import com.example.truecallerassignment.api.ProjectApiService
import com.example.truecallerassignment.mock
import com.example.truecallerassignment.repository.ProjectRepository
import com.example.truecallerassignment.storage.StorageLayer
import junit.framework.TestCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response

import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

const val SAMPLE_RESPONSE = "What do you do as an Android Developer at Truecaller"

@RunWith(MockitoJUnitRunner::class)
class ProjectViewModelTest : TestCase() {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var SUT:ProjectViewModel
    @Mock private lateinit var storageLayer: StorageLayer
    private lateinit var repository: ProjectRepository
    private val observer: Observer<String> = mock()

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        repository = ProjectRepository(ProjectApiServiceTD())
        SUT = ProjectViewModel(repository, storageLayer, true)
    }

    @Test
    fun getProjectData10thChar() = runBlocking{
        val latch = CountDownLatch(1)
        SUT.fetchLifeAsAnEngineerData10thCharacter()
        SUT.getProjectData10thChar().observeForever(observer)
//        verify(observer).onChanged(isA(String::class.java))
        val captor = ArgumentCaptor.forClass(String::class.java)
        captor.run {
            latch.await(100, TimeUnit.MILLISECONDS)
            verify(observer, times(1)).onChanged(capture())
            assertEquals(SAMPLE_RESPONSE, value)
        }
    }
}

class ProjectApiServiceTD: ProjectApiService{
    override fun callLifeAsAnEngineerData(): Call<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getLifeAsAnEngineerData(): Response<String> {
        return Response.success(SAMPLE_RESPONSE)
    }
}