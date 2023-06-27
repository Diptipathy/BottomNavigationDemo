package com.bottom.navigation

import kotlin.random.Random

object Util {

    const val ONE_MINUTE: Long = 1000*60
    const val ONE_AND_HALF_MINUTE: Long = 1000*90
    const val TWO_MINUTE: Long = 1000*120

    fun getRandomString() : String {
        val length = 2
        val allowedChars = ('A'..'Z') + ('a'..'z')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun getRandomChar(): Char {
        return ('A'..'Z').random()
    }

    fun getRandomNumber() : Int {
        return Random.nextInt(10000)
    }
}