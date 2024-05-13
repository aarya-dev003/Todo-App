package com.aarya.todoapp

import android.app.Application
import androidx.room.Room
import com.aarya.todoapp.model.repo.local.TodoDatabase

class MainApplication : Application() {
    companion object{
        lateinit var todoDatabase: TodoDatabase
    }

    override fun onCreate() {
        super.onCreate()
        todoDatabase = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            TodoDatabase.NAME
        ).build()
    }
}