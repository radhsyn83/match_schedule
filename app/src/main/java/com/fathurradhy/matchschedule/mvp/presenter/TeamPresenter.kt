package com.fathurradhy.matchschedule.mvp.presenter

import com.fathurradhy.matchschedule.mvp.model.TeamResponse
import com.fathurradhy.matchschedule.test.repository.RetrofitRepository
import com.fathurradhy.matchschedule.test.repository.TeamView

class TeamPresenter(private val view: TeamView, private val retrofitRepository: RetrofitRepository) {

    fun getTeamByLeagues(id: String){
        retrofitRepository.getTeamByLeagues(id, object : TeamView {
            override fun onDataLoaded(data: TeamResponse?, side: String) {
                view.onDataLoaded(data, side)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
    }

    fun getEmblemHome(id: String) {
        retrofitRepository.getEmblemHome(id, object : TeamView {
            override fun onDataLoaded(data: TeamResponse?, side: String) {
                view.onDataLoaded(data, side)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
    }

    fun getEmblemAway(id: String) {
        retrofitRepository.getEmblemAway(id, object : TeamView {
            override fun onDataLoaded(data: TeamResponse?, side: String) {
                view.onDataLoaded(data, side)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
    }
}