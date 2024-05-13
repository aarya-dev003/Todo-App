package com.aarya.todoapp.views

import androidx.room.TypeConverter
import java.util.Date

class DateConvertor {
    @TypeConverter
    fun fromDate(date : Date): Long{
        return date.time
    }

    @TypeConverter
    fun toDate(time: Long): Date{
        return Date(time)
    }
}