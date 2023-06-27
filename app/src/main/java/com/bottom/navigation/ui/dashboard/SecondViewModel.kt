package com.bottom.navigation.ui.dashboard

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bottom.navigation.FileUtil
import com.bottom.navigation.FileUtil.readFromFile
import com.bottom.navigation.Util.ONE_AND_HALF_MINUTE
import com.bottom.navigation.Util.ONE_MINUTE
import com.bottom.navigation.Util.TWO_MINUTE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class SecondViewModel(context: Context) : ViewModel() {

    private val _text1 = MutableLiveData<String>().apply {
        value = "Not Updated yet"
    }
    val text1: LiveData<String> = _text1

    private val _text2 = MutableLiveData<String>().apply {
        value = "Not Updated yet"
    }
    val text2: LiveData<String> = _text2

    private val _text3 = MutableLiveData<String>().apply {
        value = "Not Updated yet"
    }
    val text3: LiveData<String> = _text3

    val update1: Job  = startRepeatingJob(ONE_MINUTE) {
        Log.d("Dipti", "ONE_MINUTE: ")
        _text1.postValue(readFromFile(FileUtil.getAlphabetFile(context)))
    }

    val update2: Job  = startRepeatingJob(ONE_AND_HALF_MINUTE) {
        Log.d("Dipti", "ONE_AND_HALF_MINUTE: ")
        _text2.postValue(readFromFile(FileUtil.getNumbersFile(context)))
    }

    val update3: Job  = startRepeatingJob(TWO_MINUTE) {
        Log.d("Dipti", "TWO_MINUTE: ")
        _text3.postValue(readFromFile(FileUtil.getCharactersFile(context)))
    }


    private fun startRepeatingJob(timeInterval: Long, event: ()-> Unit): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            while (NonCancellable.isActive) {
                event()
                delay(timeInterval)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        update1.cancel()
        update2.cancel()
        update3.cancel()
    }
}