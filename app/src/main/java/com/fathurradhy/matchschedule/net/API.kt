package com.fathurradhy.matchschedule.net

import com.fathurradhy.matchschedule.domain.model.MatchModel
import com.fathurradhy.matchschedule.domain.model.TeamModel
import retrofit2.Call
import retrofit2.http.*

interface API {
    @GET("eventspastleague.php?id=4328")
    fun getPrevMatch(): Call<MatchModel>

    @GET("eventsnextleague.php?id=4328")
    fun getNextMatch(): Call<MatchModel>

    @GET("lookupteam.php")
    fun getTeam(@Query("id") id: String): Call<TeamModel>

    @GET("lookupevent.php")
    fun getMatchById(@Query("id") id: String): Call<MatchModel>
}