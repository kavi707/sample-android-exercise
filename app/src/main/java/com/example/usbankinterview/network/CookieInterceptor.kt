package com.example.usbankinterview.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

private const val cookiesKey = "appCookies"

class SendCookieInterceptor(private val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val preferences = context.getSharedPreferences("COOKIE_DATA", Context.MODE_PRIVATE)
            .getStringSet(cookiesKey, HashSet()) as HashSet<String>

        preferences.forEach { cookie ->
            builder.addHeader("cookie", cookie)
        }
        return chain.proceed(builder.build())
    }
}

class SaveCookieInterceptor(private val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())

        if (originalResponse.headers("cookie").isNotEmpty()) {
            val cookies = context.getSharedPreferences("COOKIE_DATA", Context.MODE_PRIVATE)
                .getStringSet(cookiesKey, HashSet()) as HashSet<String>

            originalResponse.headers("cookie").forEach {
                cookies.add(it)
            }

            context.getSharedPreferences("COOKIE_DATA", Context.MODE_PRIVATE)
                .edit()
                .putStringSet(cookiesKey, cookies)
                .apply()
        }

        return originalResponse
    }
}

fun OkHttpClient.Builder.setCookieStore(context: Context) : OkHttpClient.Builder {
    return this
        .addInterceptor(SendCookieInterceptor(context))
        .addInterceptor(SaveCookieInterceptor(context))
}