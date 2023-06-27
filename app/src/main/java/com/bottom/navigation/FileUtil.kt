package com.bottom.navigation

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.Environment
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream


object FileUtil {

    internal fun getAlphabetFile(context: Context): File {
        val path = "${getFilePath(context)}/Alphabets.txt"
        return File(path)
    }

    internal fun getNumbersFile(context: Context): File {
        val path = "${getFilePath(context)}/Numbers.txt"
        return File(path)
    }

    internal fun getCharactersFile(context: Context): File {
        val path = "${getFilePath(context)}/Characters.txt"
        return File(path)
    }

    internal fun getFilePath(context: Context): String {
        val file = File("${getDownloadsDirectoryPath(context)}/myFiles1/")
        if (!file.exists()){
            file.mkdirs()
        }
        return file.absolutePath
    }

    internal fun getDownloadsDirectoryPath(applicationContext: Context): String {
        return if (isExternalStorageScoped()) {
            val contextWrapper = ContextWrapper(applicationContext)
            contextWrapper.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)!!.absolutePath
        } else {
            Environment.getExternalStorageDirectory().toString() + "/Download/"
        }
    }

    internal fun isExternalStorageScoped(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            !Environment.isExternalStorageLegacy()
        else
            false
    }

    internal fun writeToFile(file: File, dataToWrite: String) {
        FileOutputStream(file, true).bufferedWriter().use { writer ->
            writer.append(dataToWrite)
            writer.newLine()
        }
    }

    internal fun writeToFile(context: Context) {
        Log.d("Dipti", "writeToFile: ")
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("Dipti", "writeToFile: CoroutineScope Start")
            val randomAlphabet = Util.getRandomString()
            val randomNumber = Util.getRandomNumber()
            val randomChar = Util.getRandomChar()
            writeToFile(getAlphabetFile(context),randomAlphabet)
            writeToFile(getNumbersFile(context),randomNumber.toString())
            writeToFile(getCharactersFile(context),randomChar.toString())
            Log.d("Dipti", "writeToFile: CoroutineScope End")
        }
    }

    internal fun readFromFile(file: File): String {
        val bufferedReader: BufferedReader = file.bufferedReader()
        return bufferedReader.use { it.readText() }
    }
}