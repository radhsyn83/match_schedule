package com.fathurradhy.matchschedule.test.repository

import com.fathurradhy.matchschedule.mvp.model.MatchResponse

interface MatchView{
    fun onDataLoaded(data: MatchResponse?)
    fun onDataError()
}