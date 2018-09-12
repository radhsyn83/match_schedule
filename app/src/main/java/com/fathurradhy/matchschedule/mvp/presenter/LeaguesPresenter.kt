package com.fathurradhy.matchschedule.mvp.presenter

import com.fathurradhy.matchschedule.mvp.model.LeaguesResponse
import com.fathurradhy.matchschedule.mvp.model.MatchResponse
import com.fathurradhy.matchschedule.test.repository.LeaguesView
import com.fathurradhy.matchschedule.test.repository.RetrofitRepository
import com.fathurradhy.matchschedule.test.repository.MatchView

class LeaguesPresenter(private val view: LeaguesView, private val retrofitRepository: RetrofitRepository) {

    fun getLeagues() {
        retrofitRepository.getLeagues(object : LeaguesView {
            override fun onDataLoaded(data: LeaguesResponse?) {
                view.onDataLoaded(data)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
    }
}