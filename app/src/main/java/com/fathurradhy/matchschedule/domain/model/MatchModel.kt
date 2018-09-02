package com.fathurradhy.matchschedule.domain.model

data class MatchModel(
        val events: ArrayList<MatchModelResult>
)

data class MatchModelResult(
        val idHomeTeam: String,
        val idAwayTeam: String,
        val idEvent: String,
        val strHomeTeam: String,
        val strAwayTeam: String,
        val intHomeScore: String,
        val intAwayScore: String,
        val strDate: String,
        val dateEvent: String,
        val strHomeGoalDetails: String,
        val strAwayGoalDetails: String,
        val intHomeShots: String,
        val strTime: String,
        val intAwayShots: String,
        val strHomeRedCards: String,
        val strAwayRedCards: String,
        val strHomeYellowCards: String,
        val strAwayYellowCards: String,
        val strHomeLineupGoalkeeper: String,
        val strAwayLineupGoalkeeper: String,
        val strHomeLineupDefense: String,
        val strAwayLineupDefense: String,
        val strHomeLineupMidfield: String,
        val strAwayLineupMidfield: String,
        val strHomeLineupForward: String,
        val strAwayLineupForward: String,
        val strHomeLineupSubstitutes: String,
        val strAwayLineupSubstitutes: String
)