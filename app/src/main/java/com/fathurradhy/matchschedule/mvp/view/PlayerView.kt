package com.fathurradhy.matchschedule.test.repository

import com.fathurradhy.matchschedule.mvp.model.PlayerResponse

interface PlayerView{
    fun onDataLoaded(data: PlayerResponse?)
    fun onDataError()
}