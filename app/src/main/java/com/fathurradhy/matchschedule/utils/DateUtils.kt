package com.fathurradhy.matchschedule.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun dateFormat(date: String) : String {
        val dateTime = SimpleDateFormat("yyyy-MM-dd").parse(date)
        val calendar = Calendar.getInstance()
        calendar.time = dateTime

        val today = Calendar.getInstance()
        val tomorrow = Calendar.getInstance()
        tomorrow.add(Calendar.DATE, +1)

        val dateTime2 = SimpleDateFormat("yyyy-MM-dd").parse(date)
        val calendar2 = Calendar.getInstance()
        calendar2.time = dateTime2

        if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
            return "Today"
        } else if (calendar.get(Calendar.YEAR) == tomorrow.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == tomorrow.get(Calendar.DAY_OF_YEAR)) {
            return "Tomorrow"
        } else {
            return SimpleDateFormat("dd MMM yyyy").format(dateTime)
        }
    }

    fun timeFormat(time: String) : String {
        val dateTime = SimpleDateFormat("HH:mm:ssXXX").parse(time)
        val calendar = Calendar.getInstance()
        calendar.time = dateTime

        return SimpleDateFormat("HH:mm").format(dateTime)
    }


}