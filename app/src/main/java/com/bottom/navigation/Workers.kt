package com.bottom.navigation

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class Workers(val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        FileUtil.writeToFile(context)
        return Result.success()
    }
}