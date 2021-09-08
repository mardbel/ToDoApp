package com.example.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.HolderLayoutBinding
import com.example.todoapp.model.Task

class TaskAdapter(private val itemClickListener: OnTaskClickListener) :
    ListAdapter<Task, TaskAdapter.ViewHolder>(TaskDiffCallback) {

    interface OnTaskClickListener {
        fun onTaskClick(task: Task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.holder_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTask: Task = getItem(position)
        val numberDisplayed = position + 1
        holder.taskId.text = numberDisplayed.toString()
        holder.taskItem.text = currentTask.task
        holder.bind(currentTask, position)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var binding = HolderLayoutBinding.bind(itemView)
        var taskId: TextView = binding.taskItemId
        var taskItem: TextView = binding.taskItemTv

        fun bind(item: Task, position: Int) {
            itemView.setOnClickListener() {
                itemClickListener.onTaskClick(item)
            }
        }

    }

    private object TaskDiffCallback : DiffUtil.ItemCallback<Task>() {

        override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Task, newItem: Task) = false
    }
}