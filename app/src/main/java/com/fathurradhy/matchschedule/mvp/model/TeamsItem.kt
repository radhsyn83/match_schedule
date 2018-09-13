package com.fathurradhy.matchschedule.mvp.model

import com.google.gson.annotations.SerializedName

data class TeamsItem(
	@field:SerializedName("idTeam")
	val idTeam: String? = null,

	@field:SerializedName("strTeam")
	val strTeam: String? = null,

	@field:SerializedName("strSport")
	val strSport: String? = null,

	@field:SerializedName("idLeague")
	val idLeague: String? = null,

	@field:SerializedName("strDescriptionEN")
	val strDescriptionEN: String? = null,

	@field:SerializedName("strCountry")
	val strCountry: String? = null,

	@field:SerializedName("strTeamBadge")
	val strTeamBadge: String? = null,

	@field:SerializedName("strTeamJersey")
	val strTeamJersey: String? = null,

	@field:SerializedName("strTeamLogo")
	val strTeamLogo: String? = null,

	@field:SerializedName("strTeamFanart1")
	val strTeamFanart1: String? = null,

	@field:SerializedName("strTeamFanart2")
	val strTeamFanart2: String? = null,

    @field:SerializedName("strYoutube")
	val strYoutube: String? = null
)