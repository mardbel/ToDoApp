package com.example.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "to_do_tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) var id: Int=0,
    @ColumnInfo(name = "task") var task: String?=""
)