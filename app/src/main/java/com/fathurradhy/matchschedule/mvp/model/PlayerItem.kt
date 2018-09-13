package com.fathurradhy.matchschedule.mvp.model

import com.google.gson.annotations.SerializedName

data class PlayerItem(
	@field:SerializedName("idPlayer")
	val idPlayer: String? = null,

	@field:SerializedName("idTeam")
	val idTeam: String? = null,

	@field:SerializedName("idSoccerXML")
	val idSoccerXML: String? = null,

	@field:SerializedName("strNationality")
	val strNationality: String? = null,

	@field:SerializedName("strPlayer")
	val strPlayer: String? = null,

	@field:SerializedName("strTeam")
	val strTeam: String? = null,

	@field:SerializedName("strSport")
	val strSport: String? = null,

	@field:SerializedName("dateBorn")
	val dateBorn: String? = null,

	@field:SerializedName("dateSigned")
	val dateSigned: String? = null,

	@field:SerializedName("strSigning")
	val strSigning: String? = null,

	@field:SerializedName("strWage")
	val strWage: String? = null,

	@field:SerializedName("strBirthLocation")
	val strBirthLocation: String? = null,

	@field:SerializedName("strDescriptionEN")
	val strDescriptionEN: String? = null,

	@field:SerializedName("strGender")
	val strGender: String? = null,

	@field:SerializedName("strPosition")
	val strPosition: String? = null,

	@field:SerializedName("strFacebook")
	val strFacebook: String? = null,

	@field:SerializedName("strTwitter")
	val strTwitter: String? = null,

	@field:SerializedName("strInstagram")
	val strInstagram: String? = null,

	@field:SerializedName("strHeight")
	val strHeight: String? = null,

	@field:SerializedName("strWeight")
	val strWeight: String? = null,

	@field:SerializedName("strThumb")
	val strThumb: String? = null,

	@field:SerializedName("strCutout")
	val strCutout: String? = null,

	@field:SerializedName("strBanner")
	val strBanner: String? = null,

	@field:SerializedName("strFanart1")
	val strFanart1: String? = null,

	@field:SerializedName("strFanart2")
	val strFanart2: String? = null


)