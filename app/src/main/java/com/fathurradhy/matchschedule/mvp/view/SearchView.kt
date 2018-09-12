package com.fathurradhy.matchschedule.test.repository

import com.fathurradhy.matchschedule.mvp.model.SearchResponse

interface SearchView{
    fun onDataLoaded(data: SearchResponse?)
    fun onDataError()
}