package com.aarya.todoapp.model.data


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title: String,
    val createdAt: Date
)

