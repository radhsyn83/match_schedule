package com.fathurradhy.matchschedule.test.repository

import com.fathurradhy.matchschedule.mvp.model.LeaguesResponse

interface LeaguesView{
    fun onDataLoaded(data: LeaguesResponse?)
    fun onDataError()
}