package com.fathurradhy.matchschedule.mvp.model

data class EventBusModel<T> (
        val msg: String?,
        val list: T?
)