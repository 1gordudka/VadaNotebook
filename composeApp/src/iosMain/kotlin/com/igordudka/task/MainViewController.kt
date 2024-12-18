package com.igordudka.task

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.igordudka.task.data.getTasksDatabase

fun MainViewController() = ComposeUIViewController {

    val dao = remember {
        getTasksDatabase().tasksDao()
    }
    App(dao)
}