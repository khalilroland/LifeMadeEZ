package com.example.plannerproject.ui.todolist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.plannerproject.CheckboxLabel
import com.example.plannerproject.data.Todo

@Composable
fun TodoDetail(
    todo: Todo,
) {
    // MA: Create an OutlinedCard for
    // each task
    OutlinedCard(
        colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color.White),
        border = BorderStroke(3.dp, Color.Black),
        // MA: Add padding to the card
        modifier = Modifier
            .padding(15.dp, 10.dp, 15.dp, 10.dp),
    ) {
                // MA: each task is passed to this
                // composable to create the tasks
                CheckboxLabel(todo)
    }
}