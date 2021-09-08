package com.example.todoapp.viewModel

import androidx.lifecycle.*
import com.example.todoapp.model.Task
import com.example.todoapp.model.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    val allTask: LiveData<MutableList<Task>> = repository.allTask.asLiveData()

    fun insert(task: String) = viewModelScope.launch {
        repository.insert(task = Task(task = task))
    }

    fun delete(task: Task) =viewModelScope.launch {
        repository.delete(task)
    }

    fun update(task: Task) =viewModelScope.launch {
        repository.update(task)
    }

    class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TaskViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}