package com.igordudka.task.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
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
import com.igordudka.task.ui.components.back
import com.igordudka.task.ui.components.blue
import com.igordudka.task.ui.components.noRippleClickable
import com.igordudka.task.ui.components.red

@Composable
fun MainScreen(
    setup: () -> Unit,
    calendar: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize().background(back).padding(vertical = 16.dp, horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Task management", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(Modifier.height(40.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("Set Up Task", color = Color.White, fontSize = 20.sp)
            Spacer(Modifier.weight(1f))
            Icon(
                Icons.Rounded.KeyboardArrowRight, "",
                tint = Color.White, modifier = Modifier.size(30.dp).noRippleClickable { setup() })
        }
        Spacer(Modifier.height(20.dp))
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color.White
        )
        Spacer(Modifier.height(30.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("Calendar", color = Color.White, fontSize = 20.sp)
            Spacer(Modifier.weight(1f))
            Icon(
                Icons.Rounded.KeyboardArrowRight, "",
                tint = Color.White, modifier = Modifier.size(30.dp).noRippleClickable { calendar() })
        }
        Spacer(Modifier.height(20.dp))
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color.White
        )
        Spacer(Modifier.height(50.dp))
        Row {
            Spacer(Modifier.weight(1f))
            Row(
                Modifier.size(150.dp).clip(CircleShape).background(red)
            ) {  }
        }
        Spacer(Modifier.height(30.dp))
        Row {

            Row(
                Modifier.size(120.dp).clip(CircleShape).background(blue)
            ) {  }
            Spacer(Modifier.weight(1f))
        }
        Spacer(Modifier.height(10.dp))
        Row {
            Spacer(Modifier.weight(1f))
            Row(
                Modifier.size(150.dp).clip(CircleShape).background(red)
            ) {  }
        }
    }
}