package com.example.plannerproject.ui.theme

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object PlannerScreen : Screen("planner_screen")
    object FriendScreen : Screen("friend_screen")
    object LeaderScreen : Screen("leader_screen")
    object AddTaskScreen: Screen("addtask_screen")


    object AddFriendScreen : Screen("add_friend_screen")

}
