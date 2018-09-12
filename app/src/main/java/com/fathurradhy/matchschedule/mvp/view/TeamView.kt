package com.fathurradhy.matchschedule.test.repository

interface TeamView<T> {

    fun onDataLoaded(data: T?, side:String)
    fun onDataError()
}