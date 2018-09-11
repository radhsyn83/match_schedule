package com.fathurradhy.matchschedule.test.repository

interface MatchRepositoryCallback<T> {

    fun onDataLoaded(data: T?)
    fun onDataError()
}