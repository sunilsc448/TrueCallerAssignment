package com.example.truecallerassignment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.truecallerassignment.ApplicationClass.Companion.application
import com.example.truecallerassignment.databinding.FragmentProjectBinding
import com.example.truecallerassignment.viewmodel.ItemsViewModel
import com.example.truecallerassignment.viewmodel.ProjectViewModel
import javax.inject.Inject

class ProjectFragment : Fragment() {
    private lateinit var parentViewModel: ProjectViewModel
    private lateinit var itemsViewModel: ItemsViewModel
    private lateinit var dataBinding: FragmentProjectBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        application.projectComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        parentViewModel = ViewModelProvider(activity!!, viewModelFactory).get(ProjectViewModel::class.java)
        itemsViewModel = ViewModelProvider(this, viewModelFactory).get(ItemsViewModel::class.java)
        inflateAndSetViewModel(inflater, container)
        initObservers()
        return dataBinding.root
    }

    private fun initObservers() {
        parentViewModel.getProjectData10thChar().observe(this, {
            if(!parentViewModel.dataLoaded) {
                parentViewModel.updateApiCount(parentViewModel.getProjectData10thChar().value)
                itemsViewModel.data10thCharChanged(parentViewModel.getProjectData10thChar().value)
            }
        })

        parentViewModel.getProjectDataEvery10thChar().observe(this, {
            if(!parentViewModel.dataLoaded) {
                parentViewModel.updateApiCount(parentViewModel.getProjectDataEvery10thChar().value)
                parentViewModel.dataEvery10thCharChanged(parentViewModel.getProjectDataEvery10thChar().value)
                itemsViewModel.dataEvery10thCharChanged(parentViewModel.getProjectDataEvery10thChar().value)
            }
        })

        parentViewModel.getProjectDataWordCount().observe(this, {
            if(!parentViewModel.dataLoaded) {
                parentViewModel.updateApiCount(parentViewModel.getProjectDataWordCount().value)
                parentViewModel.dataWordCountChanged(parentViewModel.getProjectDataWordCount().value)
                itemsViewModel.dataWordCountChanged(parentViewModel.getProjectDataWordCount().value)
            }
        })

        itemsViewModel.getViewMoreClicked().observe(this, {
            findNavController().navigate(ProjectFragmentDirections.viewMoreClicked(it))
        })
    }

    //data binding
    private fun inflateAndSetViewModel(inflater: LayoutInflater, container: ViewGroup?) {
        dataBinding = FragmentProjectBinding.inflate(inflater, container, false)
        dataBinding.viewmodel = itemsViewModel
        dataBinding.lifecycleOwner = viewLifecycleOwner
    }
}