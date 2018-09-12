package com.fathurradhy.matchschedule.mvp.model

import com.google.gson.annotations.SerializedName

data class LeaguesItem(
	@field:SerializedName("idLeague")
	val idLeague: Any? = null,

	@field:SerializedName("strLeague")
	val strLeague: String? = null,

	@field:SerializedName("strSport")
	val strSport: Any? = null,

	@field:SerializedName("strLeagueAlternate")
	val strLeagueAlternate: Any? = null
)