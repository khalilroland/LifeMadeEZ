package com.example.plannerproject.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.plannerproject.User
import com.example.plannerproject.ui.theme.Screen
import java.util.UUID


// KR:Layout for the AddFriendScreen
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFriendScreen(navController: NavController, onAddUser: (User) -> Unit) {

    // KR: val randomScore Generates a random score for the added friend
    //KR: from 1 - 10 * 10, this is to showcase the add friend feature and make more test
    //KR: friends for the demo
    val randomScore = (1..10).random() * 10

    // KR: to remeber the inputed text in the text field
    //KR: aka the friends username
    var inputText by remember { mutableStateOf("") }

    // KR: Creats a User for the added friend
    val addedFriend = User(UUID.randomUUID(), inputText, "", randomScore, false)

    // KR: Scaffold used for screen layout, organization of the buttons, etc.
    Scaffold(
        topBar = {
            // KR: TopAppBar with title and navigation icons
            //KR: all set to the theme colors of the friend screne page
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Red,
                    titleContentColor = Color.White,
                ),
                title = {
                    // KR: Displays the title "Add Friend"
                    Text(
                        text = "Add Friend",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    )
                    // KR: Row for navigation icons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        // KR: Button for navigating back to the friend screen
                        IconButton(onClick = { navController.navigate(Screen.FriendScreen.route) }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                modifier = Modifier.size(40.dp),
                                contentDescription = null
                            )
                        }
                        // KR: Button for navigating to the main screen
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
        // KR: Column for orginization
        // KR: the main functionality of the screen
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // KR: text field for entering the friend's user name
            OutlinedTextField(
                value = inputText,
                modifier = Modifier
                    .height(80.dp)
                    .width(300.dp),
                // KR: Custom colors for the text field so it matches the theme
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Red,
                    focusedLabelColor = Color.Red,
                    cursorColor = Color.Red,
                ),
                onValueChange = { inputText = it },
                // KR: custom colors for text for the text field
                //KR: also custom style and fontzie to make it look good
                textStyle = TextStyle(color = Color.Red, fontSize = 30.sp),
                label = { Text(text = "Enter Name", fontSize = 30.sp, color = Color.Red) }
            )

            // KR: Button for adding the friend
            //KR: Added Color to match the theme
            OutlinedButton(
                onClick = { onAddUser(addedFriend) },
                colors = ButtonDefaults.buttonColors(Color.Red),
                shape = CircleShape,
                border = BorderStroke(3.dp, Color.Black),
                modifier = Modifier
                    .padding(20.dp)
                    .height(60.dp)
                    .width(160.dp)
            ) {
                // KR: Text inside the button with custom colors and style to match theme
                Text(
                    text = "Add",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

