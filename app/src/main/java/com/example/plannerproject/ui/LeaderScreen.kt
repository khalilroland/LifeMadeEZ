package com.example.plannerproject.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.plannerproject.FriendViewModel
import com.example.plannerproject.LeaderboardDetail
import com.example.plannerproject.User
import com.example.plannerproject.ui.theme.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderScreen(
    navController: NavController,
    ) {
    val vm2: FriendViewModel = viewModel()
    val users by vm2.users
    val sortedUsers = users.sortedByDescending { it.score }

    // KR: Scaffold for the screen layout and organization
    Scaffold(
        topBar = {
            // KR: TopBar with title and navigation icon
            //KR: custom colors to match theme of the app and for style
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.Red,
                ),
                title = {
                    // KR: title "Leaderboard"
                    Text(
                        text = "Leaderboard",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    )
                    // KR: Row for icons on the right side
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        // KR: btn for navigating back to the main screen
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
            // KR: BottomBar with custom colors to match theme of the app and for style
            BottomAppBar(
                containerColor = Color.Black,
                contentColor = Color.Red,
            ) {

                //KR: row for the title
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    // KR: Text "LifeMade"
                    //KR: for style purpose
                    Text(
                        text = "LifeMade",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    )
                    // KR: Text "EZ"
                    // KR: white color for style purposes
                    Text(
                        text = "EZ",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        },
    ) { innerPadding ->
        // KR: Column for the user items in the screen (from leader detail)
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                // KR: Makes background color red
                .background(Color.Red),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // KR: Lazy for displaying the sorted friends + user (sorted items)
            LazyColumn(
            ){
                itemsIndexed(sortedUsers) { index, user ->
                        // KR: Display the LeaderboardDetail for each user items
                        LeaderboardDetail(index + 1, user)
                }
            }
        }
    }
}
