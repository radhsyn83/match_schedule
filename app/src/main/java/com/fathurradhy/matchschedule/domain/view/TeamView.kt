package com.fathurradhy.matchschedule.domain.view

import com.fathurradhy.matchschedule.domain.model.TeamModel

interface TeamView{
    fun onSuccess(teamModel: TeamModel)
}