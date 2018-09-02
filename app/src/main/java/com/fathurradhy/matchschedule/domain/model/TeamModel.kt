package com.fathurradhy.matchschedule.domain.model

data class TeamModel(
        val teams: ArrayList<TeamLogo>
)

data class TeamLogo(
        val strTeamBadge: String
)