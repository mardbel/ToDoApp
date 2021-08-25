package com.example.todoapp.model

import androidx.room.Dao
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val toDoDao: ToDoDao) {

    val allTask: Flow<MutableList<Task>> = toDoDao.getAllTasks()

    suspend fun insert(task: Task) {
        toDoDao.insert(task)
    }

    suspend fun deleteById(taskId: Int){
        toDoDao.deleteById(taskId)
    }
}