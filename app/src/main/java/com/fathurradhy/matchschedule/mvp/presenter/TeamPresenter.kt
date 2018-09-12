package com.fathurradhy.matchschedule.mvp.presenter

import com.fathurradhy.matchschedule.mvp.model.TeamResponse
import com.fathurradhy.matchschedule.test.repository.RetrofitRepository
import com.fathurradhy.matchschedule.test.repository.TeamView

class TeamPresenter(private val view: TeamView<TeamResponse?>, private val retrofitRepository: RetrofitRepository) {

    fun getEmblemHome(id: String) {
        retrofitRepository.getEmblemHome(id, object : TeamView<TeamResponse?> {
            override fun onDataLoaded(data: TeamResponse?, side: String) {
                view.onDataLoaded(data, side)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
    }

    fun getEmblemAway(id: String) {
        retrofitRepository.getEmblemAway(id, object : TeamView<TeamResponse?> {
            override fun onDataLoaded(data: TeamResponse?, side: String) {
                view.onDataLoaded(data, side)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
    }
}