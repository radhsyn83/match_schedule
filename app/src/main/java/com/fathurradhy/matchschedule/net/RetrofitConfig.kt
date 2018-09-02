package com.fathurradhy.matchschedule.net

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitConfig {

    val END_POINT = "https://www.thesportsdb.com/api/v1/json/1/"

    fun getApi(): API {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()

        val gson = GsonBuilder()
                .setLenient()
                .create()

        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        return retrofit.create(API::class.java!!)
    }

}