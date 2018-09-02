package com.fathurradhy.matchschedule.domain.presenter

interface MatchPresenter {
    fun loadPrevMatch()
    fun loadNextMatch()
    fun loadMatchById(id: String)
}