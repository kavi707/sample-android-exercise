package com.example.usbankinterview.network

import android.content.Context
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

object ApiManger {

    const val BASE_URL = "https://api.cryptonator.com"
    private var retrofit: Retrofit? = null

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.HEADERS
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    fun init(context: Context) {
        retrofit = getRetrofit(context)
    }

    fun getApiService(): ApiService? {
        return retrofit?.create(ApiService::class.java) ?: run {
            null
        }
    }

    private fun getRetrofit(context: Context): Retrofit {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .followRedirects(true)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true)
                .setCookieStore(context)
                .cookieJar(JavaNetCookieJar(cookieManager))
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
    }
}