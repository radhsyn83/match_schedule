package com.fathurradhy.matchschedule.mvp.model

import com.google.gson.annotations.SerializedName

data class PlayerResponse(

	@field:SerializedName("player")
	val player: List<PlayerItem>? = null
)