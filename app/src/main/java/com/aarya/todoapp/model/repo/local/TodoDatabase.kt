package com.aarya.todoapp.model.repo.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.aarya.todoapp.model.data.Todo
import com.aarya.todoapp.views.DateConvertor

@Database(entities = [Todo::class] , version = 1)
@TypeConverters(DateConvertor::class)
abstract class TodoDatabase : RoomDatabase(){
    companion object{
        const val NAME = "Todo_DB"
    }
    abstract fun getTodoDao(): TodoDAO
}