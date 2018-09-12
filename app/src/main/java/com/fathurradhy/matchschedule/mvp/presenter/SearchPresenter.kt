package com.fathurradhy.matchschedule.mvp.presenter

import com.fathurradhy.matchschedule.mvp.model.MatchResponse
import com.fathurradhy.matchschedule.mvp.model.SearchResponse
import com.fathurradhy.matchschedule.test.repository.RetrofitRepository
import com.fathurradhy.matchschedule.test.repository.MatchView
import com.fathurradhy.matchschedule.test.repository.SearchView

class SearchPresenter(private val view: SearchView, private val retrofitRepository: RetrofitRepository) {
    fun getSearchTeam(e: String) {
        retrofitRepository.getSearchTeam(e, object : SearchView {
            override fun onDataLoaded(data: SearchResponse?) {
                view.onDataLoaded(data)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
    }
}