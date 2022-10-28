package com.example.usbankinterview.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Process.THREAD_PRIORITY_BACKGROUND
import com.example.usbankinterview.network.ApiManger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FetchDataService: Service() {

    private lateinit var handlerThread: HandlerThread
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private val delay = 30000

    private var lastPrice: Float? = null

    override fun onCreate() {
        handlerThread = HandlerThread("ServiceStartArguments", THREAD_PRIORITY_BACKGROUND)
        handlerThread.start()
        val looper = handlerThread.looper
        handler = Handler(looper)

        runnable = Runnable {
            handler.postDelayed(runnable, delay.toLong())

            CoroutineScope(Dispatchers.IO).launch {
                val response = ApiManger.getApiService()?.getBitcoinValue()
                if (response?.code() == 200) {
                    val newPrice = response.body()?.ticker?.price

                    newPrice?.let { new ->
                        val dataIntent = Intent("BIT_COIN_VALUE")
                        dataIntent.putExtra("PRICE", "13.00")
                        lastPrice?.let { last ->
                            if (last.toFloat() > new.toFloat()) {
                                dataIntent.putExtra("FROM_LAST", -1)
                            } else if (last.toFloat() < new.toFloat()) {
                                dataIntent.putExtra("FROM_LAST", 1)
                            } else {
                                dataIntent.putExtra("FROM_LAST", 0)
                            }
                        }?: run {
                            dataIntent.putExtra("FROM_LAST", 0)
                        }
                        sendBroadcast(dataIntent)
                    }
                }
            }
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