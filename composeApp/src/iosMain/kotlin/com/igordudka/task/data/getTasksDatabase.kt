package com.igordudka.task.data

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

fun getTasksDatabase(): TaskDatabase {
    val dbFile = NSHomeDirectory() + "/tasks.db"
    return Room.databaseBuilder<TaskDatabase>(
        name = dbFile,
        factory = { TaskDatabase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}