package com.example.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.HolderLayoutBinding
import com.example.todoapp.model.Task

class TaskAdapter() : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private var dataList = mutableListOf<Task>()

    fun setTasksList(data: MutableList<Task>) {
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.holder_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTask: Task = dataList[position]
        holder.taskItem.text = currentTask.task
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = HolderLayoutBinding.bind(itemView)
        var taskItem: TextView = binding.taskItemTv
    }
}