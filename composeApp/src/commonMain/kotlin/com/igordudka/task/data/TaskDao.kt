package com.igordudka.task.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    suspend fun addTask(task: Task)

    @Delete
    suspend fun removeTask(task: Task)

    @Query("SELECT * FROM Task")
    fun getTasks() : Flow<List<Task>>
}