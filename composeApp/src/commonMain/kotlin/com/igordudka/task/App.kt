package com.igordudka.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.igordudka.task.data.Task
import com.igordudka.task.data.TaskDao
import com.igordudka.task.ui.CALENDAR
import com.igordudka.task.ui.EDIT_TASK
import com.igordudka.task.ui.MAIN
import com.igordudka.task.ui.SETUP
import com.igordudka.task.ui.TASK_DETAIL
import com.igordudka.task.ui.components.back
import com.igordudka.task.ui.screens.AddTaskScreen
import com.igordudka.task.ui.screens.EditTaskScreen
import com.igordudka.task.ui.screens.MainScreen
import com.igordudka.task.ui.screens.TaskScreen
import com.igordudka.task.ui.screens.calendar.CalendarScreen
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    taskDao: TaskDao
) {

    val navController = rememberNavController()
    val tasks by taskDao.getTasks().collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()



    var detailTask by remember { mutableStateOf(-1) }

    var taskToRemove by remember (detailTask){
        mutableStateOf(if(detailTask == -1) Task(
            title = "",
            deadline = "",
            description = "",
            priority = 0,
            isReminded = false,
            category = ""
        ) else tasks[detailTask])
    }

    Scaffold(
        modifier = Modifier.fillMaxSize().background(back).systemBarsPadding()
    ) {
        NavHost(navController, MAIN, modifier = Modifier.background(back)){
            composable(MAIN) {
                MainScreen({navController.navigate(SETUP)}, {navController.navigate(CALENDAR)})
            }
            composable(SETUP) {
                AddTaskScreen(
                    {
                        scope.launch {
                            taskDao.addTask(it)
                        }
                        navController.popBackStack()
                    }
                ){
                    navController.popBackStack()
                }
            }
            composable(TASK_DETAIL) {
                TaskScreen(
                    if(tasks.isNotEmpty()) tasks[detailTask] else taskToRemove, {
                    navController.popBackStack()
                    scope.launch {
                        taskDao.removeTask(tasks[detailTask])
                    }
                }, {
                    navController.navigate(EDIT_TASK)
                }, {
                    scope.launch {
                        val task = Task(
                            title = tasks[detailTask].title,
                            deadline = tasks[detailTask].deadline,
                            description = tasks[detailTask].description,
                            priority = tasks[detailTask].priority,
                            category = tasks[detailTask].category,
                            isReminded = true
                        )
                        taskDao.removeTask(tasks[detailTask])
                        taskDao.addTask(task)
                    }
                }, {
                    navController.popBackStack()
                })
            }
            composable(CALENDAR) {
                CalendarScreen(
                    tasks = tasks,
                    goToTask = {
                        detailTask = it
                        navController.navigate(TASK_DETAIL)
                    },
                    bck = {
                        navController.popBackStack()
                    }
                )
            }
            composable(EDIT_TASK) {
                EditTaskScreen(tasks[detailTask], {
                    navController.popBackStack()
                    scope.launch {
                        taskDao.removeTask(tasks[detailTask])
                        taskDao.addTask(it)
                    }

                }, {
                    navController.popBackStack()
                })
            }
        }
    }
}