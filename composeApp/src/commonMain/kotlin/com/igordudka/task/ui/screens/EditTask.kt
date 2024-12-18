package com.igordudka.task.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igordudka.task.data.Task
import com.igordudka.task.ui.components.back
import com.igordudka.task.ui.components.blue
import com.igordudka.task.ui.components.noRippleClickable
import com.igordudka.task.ui.components.red
import com.igordudka.task.ui.components.second

@Composable
fun EditTaskScreen(
    task: Task,
    edit: (Task) -> Unit,
    bck: () -> Unit
) {

    var name by remember{ mutableStateOf(task.title) }
    var description by remember{ mutableStateOf(task.description) }
    var deadline by remember { mutableStateOf(task.deadline) }
    var priority by remember { mutableStateOf(task.priority) }
    var category by remember { mutableStateOf(task.category) }

    Column(
        modifier = Modifier.fillMaxSize().background(back).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth()){
            Text(name, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White, modifier = Modifier.align(
                Alignment.Center))
            Icon(
                Icons.Rounded.KeyboardArrowLeft, "", tint = Color.White,
                modifier = Modifier.align(Alignment.CenterStart).size(40.dp).noRippleClickable { bck()})
        }
        Spacer(Modifier.height(30.dp))
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item{
                OutlinedTextField(name, {name = it},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        disabledTextColor = Color.White,
                        backgroundColor = second,
                        unfocusedBorderColor = Color.White.copy(alpha = 0f),
                        focusedBorderColor = Color.White.copy(alpha = 0f),
                        cursorColor = Color.White.copy(alpha = 0.5f)
                    ),
                    placeholder = {
                        Text("Title", fontSize = 20.sp, color = Color.White.copy(alpha = 0.8f))
                    },
                    textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
                )
                Spacer(Modifier.height(20.dp))
                OutlinedTextField(description, {description = it},
                    modifier = Modifier.fillMaxWidth().height(150.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        disabledTextColor = Color.White,
                        backgroundColor = second,
                        unfocusedBorderColor = Color.White.copy(alpha = 0f),
                        focusedBorderColor = Color.White.copy(alpha = 0f),
                        cursorColor = Color.White.copy(alpha = 0.5f)
                    ),
                    placeholder = {
                        Text("Description", fontSize = 20.sp, color = Color.White.copy(alpha = 0.8f))
                    },
                    textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
                )
                Spacer(Modifier.height(20.dp))
                OutlinedTextField(deadline, {
                        newText ->
                    deadline = newText
                },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        disabledTextColor = Color.White,
                        backgroundColor = second,
                        unfocusedBorderColor = Color.White.copy(alpha = 0f),
                        focusedBorderColor = Color.White.copy(alpha = 0f),
                        cursorColor = Color.White.copy(alpha = 0.5f)
                    ),
                    placeholder = {
                        Text("Deadline (DD.MM.YYYY)", fontSize = 20.sp, color = Color.White.copy(alpha = 0.8f))
                    },
                    textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
                )
                Spacer(Modifier.height(20.dp))
                Column(
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(
                        second
                    ).padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Priority", fontSize = 20.sp, color = Color.White.copy(alpha = 0.8f))
                    Spacer(Modifier.height(10.dp))
                    Row {
                        Column (horizontalAlignment = Alignment.CenterHorizontally){
                            Row (modifier = Modifier.size(58.dp).clip(RoundedCornerShape(16.dp))
                                .background(blue)
                                .border(1.dp, Color.White.copy(alpha = if (priority == 0) 1f else 0f), RoundedCornerShape(16.dp))
                                .noRippleClickable { priority = 0 }
                            ){  }
                            Spacer(Modifier.height(5.dp))
                            Text("Basic", fontSize = 16.sp, color = Color.White.copy(alpha = 0.6f))
                        }
                        Spacer(Modifier.width(40.dp))
                        Column (horizontalAlignment = Alignment.CenterHorizontally){
                            Row (modifier = Modifier.size(58.dp).clip(RoundedCornerShape(16.dp))
                                .background(red)
                                .border(1.dp, Color.White.copy(alpha = if (priority == 1) 1f else 0f), RoundedCornerShape(16.dp))
                                .noRippleClickable { priority = 1 }
                            ){  }
                            Spacer(Modifier.height(5.dp))
                            Text("High", fontSize = 16.sp, color = Color.White.copy(alpha = 0.6f))
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))
                OutlinedTextField(category, {category = it},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        disabledTextColor = Color.White,
                        backgroundColor = second,
                        unfocusedBorderColor = Color.White.copy(alpha = 0f),
                        focusedBorderColor = Color.White.copy(alpha = 0f),
                        cursorColor = Color.White.copy(alpha = 0.5f)
                    ),
                    placeholder = {
                        Text("Category", fontSize = 20.sp, color = Color.White.copy(alpha = 0.8f))
                    },
                    textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
                )
            }
        }
        Spacer(Modifier.weight(1f))
        Row {
            Button({bck()},
                modifier = Modifier.weight(1f).height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = red
                )
            ){
                Text("Cancel", fontSize = 22.sp, color = Color.White, fontWeight = FontWeight.Medium)
            }
            Spacer(Modifier.width(20.dp))
            Button({
                edit(
                    Task(title = name,
                        description = description,
                        category = category,
                        priority = priority,
                        deadline = formatDate(deadline),
                        isReminded = task.isReminded)
                )
            },
                modifier = Modifier.weight(1f).height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = blue
                )
            ){
                Text("Save", fontSize = 22.sp, color = Color.White, fontWeight = FontWeight.Medium)
            }
        }
    }
}