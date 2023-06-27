package com.bottom.navigation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class WorkReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("Dipti", "onReceive: ")
        context?.let {
            FileUtil.writeToFile(it)
        }
    }
}