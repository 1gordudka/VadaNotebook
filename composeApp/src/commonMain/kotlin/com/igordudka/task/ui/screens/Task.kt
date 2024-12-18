package com.igordudka.task.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igordudka.task.data.Task
import com.igordudka.task.ui.components.back
import com.igordudka.task.ui.components.blue
import com.igordudka.task.ui.components.noRippleClickable
import com.igordudka.task.ui.components.red
import com.igordudka.task.ui.components.second
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import taskmanager.composeapp.generated.resources.Res
import taskmanager.composeapp.generated.resources.time

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TaskScreen(
    task: Task,
    del: () -> Unit,
    edit: () -> Unit,
    remind: () -> Unit,
    bck: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize().background(back).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                task.title,
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
        }
        Spacer(Modifier.height(30.dp))
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(if (task.priority == 1) "HIGH" else "BASIC", fontSize = 22.sp, color = if (task.priority == 0) blue else red, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(20.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Category: ", color = Color.White, fontSize = 18.sp)
                        Text(task.category, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(Modifier.height(20.dp))
                    Text(task.description, color = Color.White, fontSize = 18.sp)
                    Spacer(Modifier.height(20.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Deadline: ", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                        Text(task.deadline, color = red, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
        Spacer(Modifier.weight(1f))
        if (task.isReminded == false){
            Row(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(second).padding(16.dp)
                    .noRippleClickable { remind() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painterResource(Res.drawable.time), "", modifier = Modifier.size(48.dp))
                Spacer(Modifier.width(15.dp))
                Text("Remind daily before\n" +
                        "deadline", fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
            }
            Spacer(Modifier.height(30.dp))
        }
        Row {
            Button({
                del()
            },
                modifier = Modifier.weight(1f).height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = red
                )
            ){
                Text("Delete", fontSize = 22.sp, color = Color.White, fontWeight = FontWeight.Medium)
            }
            Spacer(Modifier.width(20.dp))
            Button({
                edit()
            },
                modifier = Modifier.weight(1f).height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = blue
                )
            ){
                Text("Edit", fontSize = 22.sp, color = Color.White, fontWeight = FontWeight.Medium)
            }
        }
    }
}