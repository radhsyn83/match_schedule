package com.fathurradhy.matchschedule.mvp.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
	@field:SerializedName("event")
	val event: List<MatchItem>? = null
)