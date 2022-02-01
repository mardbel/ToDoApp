package com.example.todoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.ToDoApplication
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.model.Task
import com.example.todoapp.viewModel.TaskViewModel
import com.example.todoapp.viewModel.TaskViewModel.TaskViewModelFactory
import java.util.*

class MainActivity : AppCompatActivity(), TaskAdapter.OnTaskClickListener {

    private lateinit var binding: ActivityMainBinding
    private val adapter = TaskAdapter(this)
    private lateinit var taskAsMutableList: MutableList<Task>
    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((application as ToDoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.taskRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        binding.addButton.setOnClickListener {
            taskViewModel.insert(task = binding.editText.text.toString())
            Toast.makeText(this, "task added", Toast.LENGTH_LONG).show()
            binding.editText.text = null
        }

        val swipeToDeleteCallback = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                taskViewModel.delete(adapter.currentList[viewHolder.absoluteAdapterPosition])
            }

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition

                Collections.swap(taskAsMutableList, fromPosition, toPosition)
                adapter.notifyItemMoved(fromPosition, toPosition)
                return false
            }

        }

        val touchHelper = ItemTouchHelper(swipeToDeleteCallback)
        touchHelper.attachToRecyclerView(recyclerView)
        getTaskDataBase()

    }

    private fun getTaskDataBase() {
        taskViewModel.allTask.observe(this) {
            taskAsMutableList = it
            adapter.submitList(it)
        }
    }

    override fun onTaskClick(task: Task) {
        var dialog = EditDialogFragment(task)
        dialog.show(supportFragmentManager, "customDialog")
        getTaskDataBase()

    }
}