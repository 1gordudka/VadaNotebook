package com.igordudka.task.ui.screens.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igordudka.task.data.Task
import com.igordudka.task.ui.components.back
import com.igordudka.task.ui.components.noRippleClickable
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import taskmanager.composeapp.generated.resources.Res
import taskmanager.composeapp.generated.resources.filter

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CalendarScreen(
    tasks: List<Task>,
    goToTask: (Int) -> Unit,
    bck: () -> Unit
) {


    val today = remember { Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date }
    val daysList = remember {
        (0 until 30).map { today.plus(it, DateTimeUnit.DAY) }
    }
    var chosenDay by remember { mutableStateOf(0) }
    var displayTasks = remember {
        mutableStateListOf<Task>()
    }
    var isFilterDialog by remember{
        mutableStateOf(false)
    }

    if (isFilterDialog){
        FilterDialog(tasks.distinctBy { it.category }.map { it.category }, dismiss = {
            isFilterDialog =false
        }, {cat, pr->
            displayTasks.clear()
            tasks.forEach {
                if (it.deadline.substring(0, 2).toInt() >= daysList[chosenDay].dayOfMonth){
                    if (cat.contains(it.category) && pr.contains(it.priority)){
                        displayTasks.add(it)
                    }
                }
            }
            isFilterDialog = false
        })
    }

    LaunchedEffect(chosenDay){
        displayTasks.clear()
        tasks.forEach {
            if (it.deadline.substring(0, 2).toInt() >= daysList[chosenDay].dayOfMonth){
                displayTasks.add(it)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(back).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                "Calendar",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
            Icon(
                Icons.Rounded.KeyboardArrowLeft, "", tint = Color.White,
                modifier = Modifier.align(Alignment.CenterStart).size(40.dp)
                    .noRippleClickable { bck() })
            Image(
                painterResource(Res.drawable.filter), "",
                modifier = Modifier.align(Alignment.CenterEnd).size(40.dp)
                    .noRippleClickable { isFilterDialog = true})
        }
        Spacer(Modifier.height(15.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(daysList) { date ->
                DayTile(
                    day = date.dayOfMonth,
                    name = date.dayOfWeek.name.take(3), // Сокращенное название дня недели
                    isChosen = daysList.indexOf(date) == chosenDay,
                    choose = {
                        chosenDay = daysList.indexOf(date)
                    }
                )
            }
        }
        Spacer(Modifier.height(25.dp))
        LazyColumn {
            items(displayTasks){
                TaskCard(
                    it, {
                        goToTask(tasks.indexOf(it))
                    }
                )
            }
        }
    }

}

