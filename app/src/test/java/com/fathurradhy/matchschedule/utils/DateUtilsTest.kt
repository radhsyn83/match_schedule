package com.fathurradhy.matchschedule.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*
import java.text.SimpleDateFormat

class DateUtilsTest {

    @Test
    fun dateFormatTest() {
        val expect = "Tomorrow"

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val tomorrow = SimpleDateFormat("yyyy-MM-dd").format(calendar.time)

        assertEquals(expect, DateUtils.dateFormat(tomorrow))
    }

    @Test
    fun timeFormatTest() {
        val time = "19:00:00+00:00"
        val expect = "02:00"

        assertEquals(expect, DateUtils.timeFormat(time))
    }
}