package com.example.plannerproject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.plannerproject.ui.theme.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendScreen(
    // KR: List of users
    users: List<User>,
    // KR: Callback for deleting a user
    onDelete: suspend (User) -> Unit,
    // KR: controller for navigation
    navController: NavController,
) {
    // KR: Scaffold for layout, orginization, colored to match theme
    Scaffold(
        topBar = {
            // KR: Topbar with custom title and icon to match theme
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Red,
                    titleContentColor = Color.White,
                ),
                title = {
                    // KR: Text displaying the title
                    Text(
                        text = "Friendz Lizt",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    )
                    // KR: Row for nav icon
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        // KR: button for navigating back to the main screen
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
        bottomBar = {
            // KR: Bottombar for other icons and style purposes
            //KR: colors match the theme
            BottomAppBar(
                containerColor = Color.Red,
                contentColor = Color.White,
            ) {
                // KR: button for deleting selected users, used mainly to just clear the database for now
                IconButton(onClick = {
                    // KR: for launching a coroutine
                    CoroutineScope(Dispatchers.IO).launch {
                        users.forEach { user ->
                            if (user.isSelected) {
                                onDelete(user)
                            }
                        }
                    }
                }) {
                    // KR: Icon for delete
                    Icon(
                        imageVector = Icons.Default.Delete,
                        modifier = Modifier.size(40.dp),
                        contentDescription = null
                    )
                }
                // KR: Text that displays "LifeMade" for style purpuses, made it black
                Text(
                    text = "LifeMade",
                    modifier = Modifier.offset(40.dp, 0.dp),
                    fontSize = 40.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                // KR: Text displaying "EZ" for style purposes, made it white
                Text(
                    text = "EZ",
                    modifier = Modifier.offset(40.dp, 0.dp),
                    fontSize = 40.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                // KR: Row for add friend icon
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    // KR: Icon for navigating to AddFriendScreen
                    IconButton(onClick = { navController.navigate(Screen.AddFriendScreen.route) }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            modifier = Modifier.size(40.dp),
                            contentDescription = null
                        )
                    }
                }
            }
        },
    ) { innerPadding ->
        // KR: Column for the user's friend items
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // KR: LazyColumn for displaying a list of friends
            LazyColumn(
            ){
                // KR: used to display list of users except userMain (us)
                items(users) { user ->
                    if (user.userName == "userMain") {
                    } else {
                        // KR: Displays each friend
                        UserDetail(user)
                    }
                }
            }
        }
    }
}

