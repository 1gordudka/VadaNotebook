package com.igordudka.task.ui.screens.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igordudka.task.ui.components.blue
import com.igordudka.task.ui.components.noRippleClickable
import com.igordudka.task.ui.components.second

@Composable
fun DayTile(
    day: Int,
    name: String,
    isChosen: Boolean,
    choose: () -> Unit
) {

    Box(modifier = Modifier.padding(horizontal = 4.dp).clip(RoundedCornerShape(16.dp)).background(
        second)
        .border(1.5.dp, blue.copy(alpha = if (isChosen) 1f else 0f), RoundedCornerShape(16.dp))
        .noRippleClickable { choose() }
        .padding(12.dp),
        contentAlignment = Alignment.Center){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("$day", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(7.dp))
            Text(name, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.White.copy(alpha = 0.4f))
        }
    }
}