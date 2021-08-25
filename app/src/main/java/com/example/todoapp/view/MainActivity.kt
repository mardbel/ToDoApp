package com.example.todoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.ToDoApplication
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.viewModel.TaskViewModel
import com.example.todoapp.viewModel.TaskViewModel.TaskViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = TaskAdapter()


    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((application as ToDoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.TaskRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        binding.addButton.setOnClickListener {
            taskViewModel.insert(task = binding.editText.text.toString())
            Toast.makeText(this, "task added", Toast.LENGTH_LONG).show()
        }

        val swipeDelete = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                taskViewModel.deleteById(viewHolder.absoluteAdapterPosition)
            }
        }

        val touchHelper = ItemTouchHelper(swipeDelete)
        touchHelper.attachToRecyclerView(recyclerView)
        getTaskDataBase()
    }

    private fun getTaskDataBase() {
        taskViewModel.allTask.observe(this) { adapter.submitList(it) }
    }
}