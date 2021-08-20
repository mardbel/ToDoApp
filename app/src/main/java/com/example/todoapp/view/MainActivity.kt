package com.example.todoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.ToDoApplication
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.model.Task
import com.example.todoapp.viewModel.TaskViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val taskViewModel: TaskViewModel by viewModels {
            TaskViewModel.TaskViewModelFactory((application as ToDoApplication).repository)
        }

        val recyclerView = binding.TaskRecyclerView
        val adapter = TaskAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fun getTaskDataBase() {
            taskViewModel.allTask.observe(this) {
                it.let { adapter.setTasksList(it) }
            }
        }

        binding.addButton.setOnClickListener {
            val text = binding.editText.text.toString()
            val newTask = Task(1, text) //esta bien crear un objeto Task?
            taskViewModel.insert(newTask)
            Toast.makeText(this, "task added", Toast.LENGTH_LONG).show()
        }
        getTaskDataBase()
    }
}