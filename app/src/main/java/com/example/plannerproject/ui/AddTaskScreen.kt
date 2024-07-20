package com.example.plannerproject.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.plannerproject.data.Todo
import com.example.plannerproject.ui.theme.Screen
import java.util.UUID

//Screen for add task to add task to task list screen/database
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(navController: NavController, onAddTodo: (Todo) -> Unit) {

    // KR: remembers inputed task in the text field
    var inputText by remember { mutableStateOf("") }

    //KR: the next few lines of code is for notification purposes
    //KR: the plan: make notification appear when a task is added
    // KR: Accesss the current context
    //KR: for notification purposes
    val context = LocalContext.current

    // KR: for checking notification permissions
    var hasNotificationPermission by remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            )
        } else {
            mutableStateOf(true)
        }
    }

    // KR: Scaffold used for screen layout, organization of the buttons, etc.
    Scaffold(
        topBar = {
            // KR: TopAppBar with custom title and navigation icons
            //KR: Colors match the task screen theme
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Red,
                ),
                title = {
                    // KR: Text for displaying the title
                    Text(
                        text = "Add Task",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    )
                    // KR: Row for navigation icons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        // KR: for navigating back
                        IconButton(onClick = { navController.navigate(Screen.PlannerScreen.route) }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                modifier = Modifier.size(40.dp),
                                contentDescription = null
                            )
                        }
                        // KR:  for navigating to the main screen
                        IconButton(onClick = { navController.navigate(Screen.MainScreen.route) }) {
                            Icon(
                                imageVector = Icons.Default.ExitToApp,
                                modifier = Modifier.size(40.dp),
                                contentDescription = null
                            )
                        }
                    }
                })

        },
    ) {
        // KR: Column for the main functionality of the screen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // KR: used for inputting the task
            OutlinedTextField(
                value = inputText,
                modifier = Modifier
                    .height(80.dp)
                    .width(300.dp),
                // KR: Customized colors for text field
                //KR: made colors match the theme
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    focusedLabelColor = Color.White,
                    cursorColor = Color.White,
                ),
                onValueChange = { inputText = it },
                // KR: Customizd text  for the text field to make it look good and match the theme
                textStyle = TextStyle(color = Color.White, fontSize = 30.sp),
                label = { Text(text = "Enter Task", fontSize = 30.sp, color = Color.White) }
            )
            // KR: Creats a temporary task object
            //KR: used to create task items
            val temp = Todo(UUID.randomUUID(), inputText, 10, false)

            // KR: button for adding the task, takes you back to task screen when done
            OutlinedButton(
                onClick = {
                    onAddTodo(temp)
                    navController.navigate(Screen.PlannerScreen.route)
                },
                // KR: Custom btn colors and borders to match theme
                colors = ButtonDefaults.buttonColors(Color.White),
                shape = CircleShape,
                border = BorderStroke(3.dp, Color.Black),
                modifier = Modifier
                    .padding(20.dp)
                    .height(60.dp)
                    .width(160.dp)
            ) {
                // KR: Text inside btn, colors and style to match theme
                Text(
                    text = "Add",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
            }
        }
    }
}
