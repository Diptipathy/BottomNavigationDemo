package com.bottom.navigation.ui.home

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bottom.navigation.FileUtil
import com.bottom.navigation.WorkReceiver
import java.io.File

class FirstViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    internal fun createFileIfNotExists(context: Context) {
        val filePath = File(FileUtil.getDownloadsDirectoryPath(context))
        if (!filePath.exists()) {
            filePath.mkdirs()
        }
        createFile(FileUtil.getAlphabetFile(context))
        createFile(FileUtil.getNumbersFile(context))
        createFile(FileUtil.getCharactersFile(context))
    }

    private fun createFile(file: File) {
        if (!file.exists()) {
            file.createNewFile()
        }
    }

    internal fun startBackgroundWork(context: Context) {
        Log.d("Dipti", "startBackgroundWork: ")
        /*val uploadWorkRequest = PeriodicWorkRequest.Builder(Workers::class.java, 15, TimeUnit.MINUTES).build()
        WorkManager.getInstance(context)
            .enqueue(uploadWorkRequest)*/

        val alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, WorkReceiver::class.java)
        val alarmIntent: PendingIntent = PendingIntent.getBroadcast(context, 0, intent, FLAG_IMMUTABLE)
        alarmMgr.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + 1,
            1000*60,
            alarmIntent
        )
    }
}