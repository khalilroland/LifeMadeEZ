package com.example.plannerproject

// KR: Interface for repositorys
//KR: functions explained in depth in files its used
interface UserRepository {

    // KR: function to find a user
    suspend fun findUserById(user_name: String): User

    // KR:  function to update a user
    suspend fun updateUser(user: User)

    // KR: function to delete a user
    suspend fun deleteUser(user: User)

    // KR:  function to get a list of users
    suspend fun getUsers(): List<User>

    // KR: function to insert a user
    suspend fun insertUser(user: User)
}