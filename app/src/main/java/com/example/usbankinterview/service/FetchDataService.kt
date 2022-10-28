package com.example.usbankinterview.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Process.THREAD_PRIORITY_BACKGROUND


class FetchDataService: Service() {

    private lateinit var handlerThread: HandlerThread
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private val delay = 30000

    override fun onCreate() {
        handlerThread = HandlerThread("ServiceStartArguments", THREAD_PRIORITY_BACKGROUND)
        handlerThread.start()
        val looper = handlerThread.looper
        handler = Handler(looper)

        runnable = Runnable {
            handler.postDelayed(runnable, delay.toLong())
            // Do api call
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler.postDelayed(runnable, delay.toLong())
        return START_STICKY
    }

    override fun onDestroy() {
        handler.removeCallbacks(runnable)
    }
}