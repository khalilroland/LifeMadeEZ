package com.example.plannerproject

// KR: Interface for usersfetcher
// KR: will go more in debt in userfetcher screen
interface IUsersFetcher {
    // KR: function to fetch a list of users
    suspend fun fetchUsers(): List<User>
}