package com.example.data

import android.content.Context
import com.example.data.model.EndPoints.BASE_URL
import com.example.data.remote.ConnectivityStateInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceFactory {

    private const val TIME_OUT = 30_000L

    fun <T> createRepositoryApi(
        repositoryApiClass: Class<T>,
        timeOut: Long = TIME_OUT,
        timeUnit: TimeUnit = TimeUnit.MILLISECONDS,
        context: Context
    ): T {
        val myClient = OkHttpClient.Builder()
            .connectTimeout(timeOut, timeUnit)
            .readTimeout(timeOut, timeUnit)
            .addInterceptor(ConnectivityStateInterceptor(context))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(myClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(repositoryApiClass)
    }
}