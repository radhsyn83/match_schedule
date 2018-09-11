package com.fathurradhy.matchschedule.match

import com.fathurradhy.matchschedule.entity.TeamResponse
import com.fathurradhy.matchschedule.test.repository.RetrofitRepository
import com.fathurradhy.matchschedule.test.repository.TeamRepositoryCallback

class TeamPresenter(private val view: TeamView, private val retrofitRepository: RetrofitRepository) {

    fun getEmblemHome(id: String) {
        view.onShowLoading()
        retrofitRepository.getEmblemHome(id, object : TeamRepositoryCallback<TeamResponse?> {
            override fun onDataLoaded(data: TeamResponse?, side: String) {
                view.onDataLoaded(data, side)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
        view.onHideLoading()
    }

    fun getEmblemAway(id: String) {
        view.onShowLoading()
        retrofitRepository.getEmblemAway(id, object : TeamRepositoryCallback<TeamResponse?> {
            override fun onDataLoaded(data: TeamResponse?, side: String) {
                view.onDataLoaded(data, side)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
        view.onHideLoading()
    }
}