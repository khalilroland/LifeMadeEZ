package com.example.plannerproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.plannerproject.ui.theme.PlannerProjectTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plannerproject.data.Todo
import com.example.plannerproject.ui.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlannerProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PlannerProjectTheme {
    }
}



// KR: Function thats the UI for the items in the Task List
@Composable
fun CheckboxLabel(todo: Todo) {
    // KR: used remember to keep track of which items were checked
    var checked by remember { mutableStateOf(false) }
    // KR: Initialize checked with isComplete status
    checked = todo.isComplete

    // KR: Rowfor positioning
    Row(
        // KR: Modifier for padding
        modifier = Modifier.padding(15.dp),
        // KR: Alignment
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        // KR: Checkbox with colors to match theme
        // KR: also handles checked state
        Checkbox(
            checked = checked,
            colors = CheckboxDefaults.colors(Color.Red),
            onCheckedChange = { isChecked ->
                // KR: Updates task complete with checkbox state
                todo.isComplete = isChecked
                // KR: Updates the checked state
                checked = isChecked
            },
        )
        // KR: Row for displaying task point value
        Text("${todo.task}", fontSize = 30.sp, color = Color.Red,  fontWeight = FontWeight.Bold)

        Row(
            // KR: Modifier for filling the maxwidth
            modifier = Modifier.fillMaxWidth(),
            // KR: Alignment
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            // KR: style points to make them macth the theme
            Text(text = "+${todo.points} ", fontSize = 30.sp, color = Color.Red, fontWeight = FontWeight.Bold)
        }
    }
}
