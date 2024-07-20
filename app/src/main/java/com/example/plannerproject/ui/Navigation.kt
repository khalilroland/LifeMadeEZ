package com.example.plannerproject.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.plannerproject.FriendScreen
import com.example.plannerproject.FriendViewModel
import com.example.plannerproject.ui.theme.Screen
import com.example.plannerproject.ui.todolist.TodoListView
import com.example.plannerproject.ui.todolist.TodoListViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Navigation() {
    // MA: NavController for managing navigation
    val navController = rememberNavController()
    // MA: ViewModel instances for managing users and tasks
    val vm: TodoListViewModel = viewModel()
    val vm2: FriendViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController, vm = vm2)
        }
        // MA: Screen for the TodoList
        composable(
            route = Screen.PlannerScreen.route
        ){
            TodoListScreen(vm,navController)
        }
        // MA: Screen for the Friends list
        composable(
            route = Screen.FriendScreen.route
        ){
            FriendListScreen(vm2, navController)
        }
        // MA: Screen for the Leaderboard
        composable(
            route = Screen.LeaderScreen.route
        ){
            LeaderboardScreen(vm2, navController)
        }
        // MA: Screen for adding a new task
        composable(
            route = Screen.AddTaskScreen.route
        ){
            AddTaskScreen(navController,
                onAddTodo = { todo ->
                    vm.addTodo(todo)
                    navController.popBackStack()
                })
        }
        // MA: Screen for adding a new friend
        composable(
            route = Screen.AddFriendScreen.route
        ){
            AddFriendScreen(navController = navController, onAddUser = { user ->
                vm2.addUser(user)
                navController.popBackStack()
            })
        }

    }
}

@Composable
fun MainScreen(navController: NavController, vm: FriendViewModel) {
    val vm2: FriendViewModel = viewModel()
    val users by vm2.users

    val quotes = listOf(
        "Failure is success in progress!",
        "The only way to do great work is to love what you do.",
        "Don't watch the clock; do what it does. Keep going.",
        "Success is not final, failure is not fatal: It is the courage to continue that counts.",
        "Your time is limited, don't waste it living someone else's life.",
        "Believe you can and you're halfway there.",
        "The only limit to our realization of tomorrow will be our doubts of today.",
        "Success is not final, failure is not fatal: It is the courage to continue that counts.",
        "You are never too old to set another goal or to dream a new dream.",
        "In the middle of difficulty lies opportunity.",
        "The future belongs to those who believe in the beauty of their dreams.",
        "Do not wait to strike till the iron is hot, but make it hot by striking.",
        "Don't be pushed around by the fears in your mind. Be led by the dreams in your heart.",
        "The only place where success comes before work is in the dictionary.",
        "A journey of a thousand miles begins with a single step.",
    )




    val initialQuote = remember { quotes.random() }
//    var currentUser by remember { mutableStateOf(users.value[0].userName) }
    //main column for text and buttons
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        //Settings for the Title, made the second half red to give it a slicker look
        Row{
            Text(
                text = "LifeMade",
                fontSize =50.sp,
                modifier = Modifier.offset(0.dp, 50.dp),
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "EZ",
                color = Color.Red,
                fontSize =50.sp,
                modifier = Modifier.offset(0.dp, 50.dp),
                fontWeight = FontWeight.Bold
            )
        }

        //Quote of the day Text, we plane to have an array of random quotes that will randomly
        //change everytime you open the main screen
Row {
    // MA: Display welcome message and the Quote of the Day
    Text(
        text = "Welcome back, ",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier.offset(0.dp, 50.dp)
    )
    Text(
        text = "${
            if (!users.isEmpty()) {
                users[0].userName
            } else {
                ""
            }
        }",
        fontSize = 20.sp,
        modifier = Modifier.offset(0.dp, 50.dp),
        fontWeight = FontWeight.Bold,
        color = Color.Red,
    )
}

        Text(
            text = "Quote of the Day: ",
            fontWeight = FontWeight.Bold,
            fontSize =20.sp,
            modifier = Modifier.offset(0.dp, 60.dp)
        )
        Text (
            text = initialQuote,
            fontSize =20.sp,
            modifier = Modifier.offset(0.dp, 60.dp),
            textAlign = TextAlign.Center
        )
        // these buttons will take you to each of the different screens: Planner, Friends list
        //and Leaderboard
        // MA: Buttons to navigate to different screens
        OutlinedButton(onClick ={navController.navigate(Screen.PlannerScreen.route)},
            //shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color.White),
            shape = CircleShape,
            border = BorderStroke(3.dp, Color.Red),
            modifier = Modifier
                .offset(0.dp, 100.dp)
                .height(75.dp)
                .width(300.dp)
        ){
            Text(text = "Tazk Lizt", fontSize = 40.sp, color = Color.Red)
        }

        OutlinedButton(onClick ={navController.navigate(Screen.FriendScreen.route)},
            //shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color.Red),
            shape = CircleShape,
            border = BorderStroke(3.dp, Color.Black),
            modifier = Modifier
                .offset(0.dp, 140.dp)
                .height(75.dp)
                .width(300.dp)
        ){
            Text(text = "Friendz Lizt", fontSize = 40.sp)
        }

        OutlinedButton(onClick ={navController.navigate(Screen.LeaderScreen.route)},
            //shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color.Black),
            shape = CircleShape,
            border = BorderStroke(3.dp, Color.Red),
            modifier = Modifier
                .offset(0.dp, 180.dp)
                .height(75.dp)
                .width(300.dp)
        ){
            Text(text = "Leaderboard", fontSize = 40.sp,color = Color.Red)
        }

        //this section displays the users current score, the users score will increase
        //depening on what task he completes in his planner
        //the users position on the leaderboard will also increase or decrease if he has
        //more or less points then his friends on his friends list
        // MA: Display user's current score
        Text(
            text = "Current Score:",
            fontSize =30.sp,
            modifier = Modifier.offset(0.dp, 220.dp),
            fontWeight = FontWeight.Bold
        )
        //we currently set the users score to 999 for this prototype
        Row {
            Text(
                text = "${
                    if (!users.isEmpty()){
                        users[0].score
                    } else {
                        ""
                    }
                }",
                fontSize =50.sp,
                modifier = Modifier.offset(0.dp, 230.dp),
                fontWeight = FontWeight.Bold,
                color = Color.Red,
            )
        }




    }
}

@OptIn(ExperimentalLayoutApi::class)
@ExperimentalFoundationApi
@Composable
fun TodoListScreen(
    vm: TodoListViewModel,
    navController: NavController
) {
    val todos by vm.todos

    // MA: calling the todoList screen
    TodoListView(
        todos,
        onDelete =vm::deleteTodo,
        navController,
        context = LocalContext.current
    )
}

@ExperimentalFoundationApi
@Composable
fun FriendListScreen(
    vm: FriendViewModel,
    navController: NavController
) {
    val users by vm.users

    // MA: calling the FriendView screen
    FriendScreen(
        users,
        onDelete =vm::deleteUser,
        navController
    )
}

@ExperimentalFoundationApi
@Composable
fun LeaderboardScreen(
    vm: FriendViewModel,
    navController: NavController
) {
    val users by vm.users
    val waiting by vm.wait

    // MA: calling the Leader screen
    LeaderScreen(
        navController
    )
}