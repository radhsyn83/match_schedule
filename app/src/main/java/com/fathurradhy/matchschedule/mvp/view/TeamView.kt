package com.fathurradhy.matchschedule.test.repository

import com.fathurradhy.matchschedule.mvp.model.TeamResponse

interface TeamView {

    fun onDataLoaded(data: TeamResponse?, side:String)
    fun onDataError()
}