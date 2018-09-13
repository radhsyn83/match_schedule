package com.fathurradhy.matchschedule.net

import com.fathurradhy.matchschedule.mvp.model.*
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

    @GET("lookup_all_teams.php")
    fun getTeamList(@Query("id") id: String): Call<TeamResponse>

    @GET("lookup_all_players.php")
    fun getPlayerList(@Query("id") id: String): Call<PlayerResponse>

    @GET("searchevents.php")
    fun getSearchTeam(@Query("e") e: String): Call<SearchResponse>

    @GET("all_leagues.php")
    fun getLeagues(): Call<LeaguesResponse>
}