package com.fathurradhy.matchschedule.domain.model

data class TeamModel(
        val teams: ArrayList<TeamModeLResult>
)

data class TeamModeLResult(
        val idTeam: String,
        val strTeam: String,
        val strAlternate: String,
        val strSport: String,
        val strStadium: String,
        val strTeamBadge: String
)