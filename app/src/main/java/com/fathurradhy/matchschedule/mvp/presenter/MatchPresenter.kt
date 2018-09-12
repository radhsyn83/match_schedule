package com.fathurradhy.matchschedule.mvp.presenter

import com.fathurradhy.matchschedule.mvp.model.MatchResponse
import com.fathurradhy.matchschedule.test.repository.RetrofitRepository
import com.fathurradhy.matchschedule.test.repository.MatchView

class MatchPresenter(private val view: MatchView, private val retrofitRepository: RetrofitRepository) {

    fun getPrevMatch(id: String) {
        retrofitRepository.getPrevMatch(id, object : MatchView {
            override fun onDataLoaded(data: MatchResponse?) {
                view.onDataLoaded(data)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
    }

    fun getNextMatch(id: String) {
        retrofitRepository.getNextMatch(id, object : MatchView {
            override fun onDataLoaded(data: MatchResponse?) {
                view.onDataLoaded(data)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
    }

    fun getDetailMatch(id: String) {
        retrofitRepository.getDetailMatch(id, object : MatchView {
            override fun onDataLoaded(data: MatchResponse?) {
                view.onDataLoaded(data)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
    }
}