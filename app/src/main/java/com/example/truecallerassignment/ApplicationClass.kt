package com.example.truecallerassignment

import android.app.Application
import com.example.truecallerassignment.component.DaggerProjectComponent
import com.example.truecallerassignment.component.ProjectComponent
import com.example.truecallerassignment.module.StorageModule
import com.example.truecallerassignment.modules.ApiModule
import com.example.truecallerassignment.modules.AppModule

class ApplicationClass:Application() {
    lateinit var projectComponent: ProjectComponent
    override fun onCreate() {
        super.onCreate()
        application = this
        projectComponent = initProjectComponent(this)
    }

    private fun initProjectComponent(appClass: ApplicationClass):ProjectComponent{
        return  DaggerProjectComponent.builder().
        appModule(AppModule(appClass)).
        apiModule(ApiModule()).
        storageModule(StorageModule()).
        build()
    }

    companion object{
        lateinit var application:ApplicationClass
            private set
    }
}