package com.fathurradhy.matchschedule.domain.view

import com.fathurradhy.matchschedule.domain.model.MatchModelResult

interface MatchView{
    fun onSuccess(matchModelResult: ArrayList<MatchModelResult>)
    fun onFailed(msg: String)
}