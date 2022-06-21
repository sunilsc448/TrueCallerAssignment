package com.example.truecallerassignment.component

import com.example.truecallerassignment.module.StorageModule
import com.example.truecallerassignment.modules.ApiModule
import com.example.truecallerassignment.modules.AppModule
import com.example.truecallerassignment.modules.ViewModelModule
import com.example.truecallerassignment.view.DetailsFragment
import com.example.truecallerassignment.view.ProjectActivity
import com.example.truecallerassignment.view.ProjectFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, ApiModule::class, StorageModule::class])
interface ProjectComponent {
    fun inject(activity: ProjectActivity)
    fun inject(fragment: ProjectFragment)
    fun inject(fragment: DetailsFragment)
}