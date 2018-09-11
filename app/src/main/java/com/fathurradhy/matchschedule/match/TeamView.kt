package com.fathurradhy.matchschedule.match

import com.fathurradhy.matchschedule.entity.TeamResponse
import com.fathurradhy.matchschedule.test.repository.TeamRepositoryCallback

interface TeamView : TeamRepositoryCallback<TeamResponse> {
    fun onShowLoading()
    fun onHideLoading()
}