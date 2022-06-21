package com.example.truecallerassignment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.truecallerassignment.databinding.FragmentDetailsBinding
import com.example.truecallerassignment.utility.ProjectConstants.Companion.EVERY_TENTH_CHAR
import com.example.truecallerassignment.ApplicationClass.Companion.application
import com.example.truecallerassignment.viewmodel.ProjectViewModel
import javax.inject.Inject

private const val ARG_PARAM1 = "list_type"

class DetailsFragment : Fragment() {
    private var lisType: String? = null
    private lateinit var parentViewModel: ProjectViewModel
    private lateinit var dataBinding: FragmentDetailsBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        application.projectComponent.inject(this)
        arguments?.let {
            lisType = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        parentViewModel = ViewModelProvider(activity!!, viewModelFactory).get(ProjectViewModel::class.java)
        inflateAndSetViewModel(inflater, container)
        return dataBinding.root
    }

    //data binding
    private fun inflateAndSetViewModel(inflater: LayoutInflater, container: ViewGroup?) {
        dataBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        dataBinding.title = lisType
        dataBinding.viewModel = parentViewModel
        if(lisType?.equals(EVERY_TENTH_CHAR) == true){
            dataBinding.list = parentViewModel.getEvery10thCharList().value
        }else{
            dataBinding.list = parentViewModel.getWordsList().value
        }
        dataBinding.lifecycleOwner = viewLifecycleOwner
    }
}