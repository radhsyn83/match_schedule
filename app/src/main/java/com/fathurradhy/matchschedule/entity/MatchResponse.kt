package com.fathurradhy.matchschedule.entity

import com.google.gson.annotations.SerializedName

data class MatchResponse(

	@field:SerializedName("events")
	val events: List<EventsItem>? = null
)