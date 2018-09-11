package com.fathurradhy.matchschedule.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofit {
    val END_POINT = "https://www.thesportsdb.com/api/v1/json/1/"

    private fun iniRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(END_POINT).addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun <T> createService(service: Class<T>): T {
        return iniRetrofit().create(service)
    }

}