package com.aarya.todoapp.model.repo.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aarya.todoapp.model.data.Todo

@Dao
interface TodoDAO {
    @Query("SELECT * FROM TODO")
    fun getAllTodo(): LiveData<List<Todo>>

    @Insert
    fun addTodo(todo: Todo)

    @Query("DELETE FROM TODO WHERE id = :id")
    fun deleteTodo(id: Int)
}