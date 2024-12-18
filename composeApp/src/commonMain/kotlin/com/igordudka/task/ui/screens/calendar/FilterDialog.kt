package com.igordudka.task.ui.screens.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.igordudka.task.ui.components.back
import com.igordudka.task.ui.components.blue
import com.igordudka.task.ui.components.second

@Composable
fun FilterDialog(
    categories: List<String>,
    dismiss: () -> Unit,
    done: (List<String>, List<Int>) -> Unit
) {

    var chosenCat = remember {
        mutableStateListOf<String>()
    }
    var chosenPriority = remember {
        mutableStateListOf<Int>()
    }

    Dialog({dismiss()}){
        Column(
            modifier = Modifier.padding(horizontal = 16.dp).clip(RoundedCornerShape(16.dp)).background(back).padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Filter by Category", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(Modifier.height(25.dp))
            LazyColumn (
                modifier = Modifier.sizeIn(maxHeight = 150.dp)
            ){
                items(categories){
                    FilterItem(it, chosenCat.contains(it)){i->
                        if (i){
                            chosenCat.add(it)
                        }else {
                            chosenCat.remove(it)
                        }
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
            Text("Filter by Priority", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(Modifier.height(25.dp))
            LazyColumn {
                item {
                    FilterItem("Basic", chosenPriority.contains(0)){i->
                        if (i){
                            chosenPriority.add(0)
                        }else{
                            chosenPriority.remove(0)
                        }
                    }
                    FilterItem("High", chosenPriority.contains(1)){i->
                        if (i){
                            chosenPriority.add(1)
                        }else{
                            chosenPriority.remove(1)
                        }
                    }
                }
            }
            Spacer(Modifier.height(25.dp))
            Button({
                done(chosenCat.toList(), chosenPriority.toList())
            },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = blue
                )
            ){
                Text("Done", fontSize = 22.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun FilterItem(
    name: String,
    chosen: Boolean,
    choose: (Boolean) -> Unit
) {

    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(name, fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
            Spacer(Modifier.weight(1f))
            Checkbox(chosen, {choose(it)},
                modifier = Modifier.size(25.dp).clip(RoundedCornerShape(6.dp)),
                colors = CheckboxDefaults.colors(
                    uncheckedColor = second,
                    checkedColor = blue
                )
            )
        }
        Spacer(Modifier.height(15.dp))
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 0.5.dp,
            color = Color.White.copy(alpha = 0.5f)
        )
    }
}