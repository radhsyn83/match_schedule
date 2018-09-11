package com.fathurradhy.matchschedule.match

import com.fathurradhy.matchschedule.entity.MatchResponse
import com.fathurradhy.matchschedule.test.repository.RetrofitRepository
import com.fathurradhy.matchschedule.test.repository.MatchRepositoryCallback

class MatchPresenter(private val view: MatchView, private val retrofitRepository: RetrofitRepository) {

    fun getPrevMatch(id: String) {
        view.onShowLoading()
        retrofitRepository.getPrevMatch(id, object : MatchRepositoryCallback<MatchResponse?> {
            override fun onDataLoaded(data: MatchResponse?) {
                view.onDataLoaded(data)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
        view.onHideLoading()
    }

    fun getNextMatch(id: String) {
        view.onShowLoading()
        retrofitRepository.getNextMatch(id, object : MatchRepositoryCallback<MatchResponse?> {
            override fun onDataLoaded(data: MatchResponse?) {
                view.onDataLoaded(data)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
        view.onHideLoading()
    }

    fun getDetailMatch(id: String) {
        view.onShowLoading()
        retrofitRepository.getDetailMatch(id, object : MatchRepositoryCallback<MatchResponse?> {
            override fun onDataLoaded(data: MatchResponse?) {
                view.onDataLoaded(data)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
        view.onHideLoading()
    }
}