package com.example.truecallerassignment.view

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.truecallerassignment.ApplicationClass
import com.example.truecallerassignment.R
import com.example.truecallerassignment.databinding.ActivityProjectBinding
import com.example.truecallerassignment.viewmodel.ProjectViewModel
import javax.inject.Inject
import com.example.truecallerassignment.BR
import com.example.truecallerassignment.model.DataState
import kotlinx.android.synthetic.main.activity_project.*

class ProjectActivity : BaseActivity() {
    private lateinit var projectViewModel: ProjectViewModel
    private lateinit var dataBinding:ActivityProjectBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApplicationClass.application.projectComponent.inject(this)
        projectViewModel = ViewModelProvider(this, viewModelFactory).get(ProjectViewModel::class.java)
        inflateAndSetViewModel()
        observeChangesRequiredForView()
    }

    private fun setNetworkState() {
        val isNetwork = connectivityLiveData.connectivityManager.activeNetworkInfo?.isConnected
        projectViewModel.updateNetworkStatus(isNetwork)
    }

    //observers
    private fun observeChangesRequiredForView() {
        projectViewModel.getLoadingStateLiveData().observe(this, {
            if(it.equals(DataState.LOADING)){
                progressBar.visibility = View.VISIBLE
            }else{
                progressBar.visibility = View.GONE
            }
        })

        //Check for Network availability and get data
        connectivityLiveData.observe(this, { isNetworkAvailable ->
            projectViewModel.updateNetworkStatus(isNetworkAvailable)
        })
    }

    //dataBinding
    private fun inflateAndSetViewModel() {
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_project)
        dataBinding.setVariable(BR.viewmodel, projectViewModel)
    }
}