package com.fathurradhy.matchschedule.match

import com.fathurradhy.matchschedule.entity.MatchResponse
import com.fathurradhy.matchschedule.test.repository.MatchRepositoryCallback

interface MatchView : MatchRepositoryCallback<MatchResponse> {
    fun onShowLoading()
    fun onHideLoading()
}