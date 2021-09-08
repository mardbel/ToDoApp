package com.example.todoapp.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface ToDoDao {

    @Query("SELECT * FROM to_do_tasks")
    fun getAllTasks(): Flow<MutableList<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Query("DELETE FROM to_do_tasks")
    suspend fun deleteAll()

    @Delete//("DELETE FROM to_do_tasks WHERE id = :taskId ")
    suspend fun delete(task: Task)

    /*@Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(task: Task)*/

    @Query("UPDATE to_do_tasks SET task = :task1 WHERE id = :tid")
    suspend fun updateTask(tid: Int, task1: String?)


}