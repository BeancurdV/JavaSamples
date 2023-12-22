package com.beancurdv.techdojo

import android.util.Log

class ComputeTimeSample {
    fun run() {
        var i = System.currentTimeMillis()
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
        i = System.currentTimeMillis() - i
        Log.e("BD_TIME", "execute : " + i + "ms")
    }
}