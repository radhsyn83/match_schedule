package com.fathurradhy.matchschedule.mvp.model

import com.google.gson.annotations.SerializedName

data class MatchResponse(

	@field:SerializedName("events")
	val events: List<MatchItem>? = null
)