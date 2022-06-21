package com.example.truecallerassignment.storage

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream

interface StorageService {
    fun readData():String?
    fun writeData(value:String):Boolean
    fun isExternalStorageAvailable():Boolean
    fun isExternalStorageReadOnly():Boolean
    fun check(): Boolean
}