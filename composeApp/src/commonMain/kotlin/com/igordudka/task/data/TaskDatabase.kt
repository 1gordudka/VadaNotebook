package com.igordudka.task.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    [Task::class],
    version = 1
)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun tasksDao() : TaskDao
}