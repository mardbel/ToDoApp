package com.example.todoapp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ToDoDao {

    @Query("SELECT * FROM to_do_tasks")
    fun getAllTasks(): Flow<MutableList<Task>>  //debe ser flow si o si?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Query("DELETE FROM to_do_tasks")
    suspend fun deleteAll()


}