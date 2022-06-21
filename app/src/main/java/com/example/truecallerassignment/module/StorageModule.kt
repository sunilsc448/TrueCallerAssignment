package com.example.truecallerassignment.module

import android.app.Application
import android.os.Environment
import com.example.truecallerassignment.storage.StorageLayer
import com.example.truecallerassignment.storage.StorageService
import dagger.Module
import dagger.Provides
import java.io.*

const val FILE_PATH = "MyFileStorage"
const val FILE_NAME = "trueCaller"

@Module
class StorageModule {
    @Provides
    fun getFilePath():String{
        return FILE_PATH
    }

    @Provides
    fun getFile(application: Application, filePath:String):File{
        return File(application.getExternalFilesDir(filePath), FILE_NAME)
    }

    @Provides
    fun getStorageService(filePath: String, file: File):StorageService{
        return StorageLayer(filePath, file)
    }
}