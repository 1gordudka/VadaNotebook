package com.igordudka.task.ui.screens.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igordudka.task.data.Task
import com.igordudka.task.ui.components.blue
import com.igordudka.task.ui.components.noRippleClickable
import com.igordudka.task.ui.components.red
import com.igordudka.task.ui.components.second

@Composable
fun TaskCard(
    task: Task,
    choose: () -> Unit
) {

    Row(
        modifier = Modifier.padding(vertical = 6.dp, horizontal = 8.dp).fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(second).padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
       Column (Modifier.weight(0.7f)){
           Row(horizontalArrangement = Arrangement.Center) {
               Text(task.title, fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Medium)
               Spacer(Modifier.width(10.dp))
               Text(task.category, fontSize = 14.sp, color = Color.White.copy(alpha = 0.4f), fontWeight = FontWeight.Medium)
           }
           Spacer(Modifier.height(15.dp))
           Text(if (task.priority == 1) "HIGH priority" else "BASIC priority", color = if (task.priority == 1) red else blue,
               fontSize = 16.sp, fontWeight = FontWeight.Bold)
           Spacer(Modifier.height(15.dp))
           Text("Deadline: ${task.deadline}", fontSize = 16.sp, color = Color.White.copy(alpha = 0.7f), fontWeight = FontWeight.Medium)
       }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Rounded.KeyboardArrowRight, "", tint = Color.White,
                modifier = Modifier.size(50.dp).noRippleClickable { choose() })
        }
    }
}