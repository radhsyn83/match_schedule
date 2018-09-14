package com.fathurradhy.matchschedule.utils

data class Favorite(val id: Long?, val matchId: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val FAVORITE_ID: String = "FAVORITE_ID"
        const val FAVORITE_FROM: String = "FAVORITE_FROM"
    }
}