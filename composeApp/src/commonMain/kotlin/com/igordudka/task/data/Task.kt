package com.igordudka.task.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    val title: String,
    val description: String,
    val deadline: String,
    val priority: Int,
    val category: String,
    val isReminded: Boolean,
    @PrimaryKey(true)
    val id: Int = 0,
)
