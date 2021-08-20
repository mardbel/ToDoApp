package com.example.todoapp

import android.app.Application
import com.example.todoapp.model.TaskDatabase
import com.example.todoapp.model.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ToDoApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { TaskDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { TaskRepository(database.taskDao()) }

}