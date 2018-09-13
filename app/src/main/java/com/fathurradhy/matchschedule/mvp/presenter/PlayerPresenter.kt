package com.fathurradhy.matchschedule.mvp.presenter

import com.fathurradhy.matchschedule.mvp.model.PlayerResponse
import com.fathurradhy.matchschedule.test.repository.PlayerView
import com.fathurradhy.matchschedule.test.repository.RetrofitRepository

class PlayerPresenter(private val view: PlayerView, private val retrofitRepository: RetrofitRepository) {

    fun getPlayerByTeam(id: String) {
        retrofitRepository.getPlayerByTeam(id, object : PlayerView {
            override fun onDataLoaded(data: PlayerResponse?) {
                view.onDataLoaded(data)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
    }
}