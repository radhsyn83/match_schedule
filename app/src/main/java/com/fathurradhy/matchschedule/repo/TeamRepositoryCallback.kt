package com.fathurradhy.matchschedule.test.repository

interface TeamRepositoryCallback<T> {

    fun onDataLoaded(data: T?, side:String)
    fun onDataError()
}