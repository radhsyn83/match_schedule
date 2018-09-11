package com.fathurradhy.matchschedule.network

import com.fathurradhy.matchschedule.entity.MatchResponse
import com.fathurradhy.matchschedule.entity.TeamResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRepository {
    @GET("eventspastleague.php")
    fun getPassMatch(@Query("id") id: String): Call<MatchResponse>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id: String): Call<MatchResponse>

    @GET("lookupevent.php")
    fun getMatchById(@Query("id") id: String): Call<MatchResponse>

    @GET("lookupteam.php")
    fun getTeam(@Query("id") id: String): Call<TeamResponse>
}