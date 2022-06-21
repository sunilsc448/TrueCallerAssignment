package com.example.truecallerassignment.storage

import android.os.Environment
import java.io.*
import javax.inject.Inject

class StorageLayer @Inject constructor(private val filePath:String, private val file: File) :StorageService {
    override fun readData(): String? {
        var text: String? = null
        val fileInputStream = FileInputStream(file)
        val fileInputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(fileInputStreamReader)
        val stringBuilder: StringBuilder = StringBuilder()
        while (run {
                text = bufferedReader.readLine()
                text
            } != null) {
            stringBuilder.append(text)
        }
        fileInputStream.close()
        return stringBuilder.toString()
    }


    override fun writeData( value:String): Boolean {
        var isSuccessful = false
        try {
            val fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(value.toByteArray())
            fileOutputStream.close()
            isSuccessful = true
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return isSuccessful
    }

    override fun isExternalStorageReadOnly(): Boolean {
        val extStorageState = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)
    }

    override fun isExternalStorageAvailable(): Boolean {
        val extStorageState = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED.equals(extStorageState)
    }

    override fun check(): Boolean{
        return file.exists()
    }
}