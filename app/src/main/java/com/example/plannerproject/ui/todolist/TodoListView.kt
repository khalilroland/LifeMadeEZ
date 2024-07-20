package com.example.plannerproject.ui.todolist

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.plannerproject.FriendViewModel
import com.example.plannerproject.ModalDialogue
import com.example.plannerproject.ModalViewModel
import com.example.plannerproject.User
import com.example.plannerproject.data.Todo
import com.example.plannerproject.ui.theme.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar

// MA: Composable function to display a list of the tasks
@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListView(
    // MA: The Tasks to be displayed
    todos: List<Todo>,
    // MA: Callback function for deleting a Todo
    onDelete: suspend (Todo) -> Unit,
    // MA: NavController for navigating between screens
    navController: NavController,
    // MA: Context for various operations
    context: Context
) {
    // MA: this is the view model for user
    // here we retrieve the list for users
    val vm2: FriendViewModel = viewModel()
    val _users by vm2.users

    // MA: this variable determine if all Todos are checked
    var isVisible by remember { mutableStateOf(true) }

    // MA: ViewModel instance for managing modals
    val modalViewModel: ModalViewModel = viewModel()

    // MA: Scaffold composable for our layout structure
    Scaffold(
        topBar = {
            // MA: TopAppBar with a title and action buttons
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Red,
                ),
                title = {
                    Text(
                        text = "Tazk Lizt",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    )
                    // MA: Row for placing icons on the top app bar
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        // MA: IconButton to open the calendar app
                        IconButton(onClick = { openCalendarApp(context) }) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                modifier = Modifier.size(40.dp),
                                tint = Color.Red,
                                contentDescription = null
                            )
                        }
                        // MA: IconButton to navigate to the main screen
                        IconButton(onClick = { navController.navigate(Screen.MainScreen.route) }) {
                            Icon(
                                imageVector = Icons.Default.ExitToApp,
                                modifier = Modifier.size(40.dp),
                                tint = Color.Red,
                                contentDescription = null
                            )
                        }
                    }
                })
        },

        bottomBar = {
            // MA: BottomAppBar of the scaffold
            BottomAppBar(
                containerColor = Color.White,
                contentColor = Color.Red,
            ) {
                // MA: Modal dialogue for showing the confirm Dialogue when deleting
                ModalDialogue(
                    modalViewModel
                )

                // MA: LaunchedEffect for continuously checking if all Todos are completed
                LaunchedEffect(true) {
                    while (true) {
                        isVisible = todos.all { it.isComplete }
                        delay(1000L)
                    }
                }

                // MA: IconButton to show when all Todos are completed
                if (isVisible) {
                    IconButton(onClick = {
                        modalViewModel.show(i = {
                            // MA: CoroutineScope to update user scores and delete Todos
                            CoroutineScope(Dispatchers.IO).launch {
                                val tempval = _users[0].score + 10 * todos.size
                                vm2.updateUser(User(_users[0].id, _users[0].userName, _users[0].password, tempval, _users[0].isSelected))
                                for (i in todos) {
                                    onDelete(i)
                                }
                            }
                        })
                    }) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            modifier = Modifier.size(40.dp),
                            tint = Color.Red,
                            contentDescription = null
                        )
                    }
                }

                // MA: Row for displaying the user's points and adding a new task
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    // MA: Text displaying user points
                    Text(
                        text = (
                                "YOUR POINTS: ${
                                    try {
                                        _users[0].score
                                    } catch (err: Exception) {

                                    }
                                }"
                                ),
                        modifier = Modifier.offset(-42.dp, 11.dp),
                        fontSize = 25.sp,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )

                    // MA: IconButton to navigate to the Add Task screen
                    IconButton(onClick = { navController.navigate(Screen.AddTaskScreen.route) }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            modifier = Modifier.size(40.dp),
                            contentDescription = null,
                            tint = Color.Red,
                        )
                    }
                }
            }
        },
    ) { innerPadding ->
        // MA: Column for the main content
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                // MA: Background color for the screen
                .background(Color.Red),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // MA: LazyColumn for displaying the list of Todos
            LazyColumn(
                modifier = Modifier.padding(top = 5.dp)
            ) {
                items(todos) { todo ->
                    // MA: Composable function to display details of a Todo
                    TodoDetail(todo)
                }
            }
        }
    }
}

// MA: Function to open the calendar app using INTENTS
private fun openCalendarApp(context: Context) {
    val beginTime: Calendar = Calendar.getInstance()
    beginTime.set(2012, 0, 19, 7, 30)

    val endTime: Calendar = Calendar.getInstance()
    endTime.set(2012, 0, 19, 8, 30)

    val intent: Intent = Intent(Intent.ACTION_INSERT)
        .setData(Events.CONTENT_URI)
        .putExtra(
            CalendarContract.EXTRA_EVENT_BEGIN_TIME,
            beginTime.getTimeInMillis()
        )
        .putExtra(
            CalendarContract.EXTRA_EVENT_END_TIME,
            endTime.getTimeInMillis()
        )
        .putExtra(Events.TITLE, "Yoga")
        .putExtra(Events.DESCRIPTION, "Group class")
        .putExtra(Events.EVENT_LOCATION, "The gym")
        .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY)
        .putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com")

    // MA: Start the calendar app activity
    startActivity(context, intent, null)
}