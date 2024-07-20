package com.example.plannerproject

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.plannerproject.data.TodoDatabase

// KR: Repository for handling User data  using Room
//implements UserRepository -> interface
class UserOfflineRepository(app: Application, private val context: Context) : UserRepository {

    // KR: val db is a database instance
    private val db: TodoDatabase

    // KR: Init block: primary use is to create database
    init {
        // KR: Creates the database using application context
        // KR: db name is "todo.db"
        db = Room.databaseBuilder(app, TodoDatabase::class.java, "todo.db")
            // KR: drops and recreats database on version change
            .fallbackToDestructiveMigration()
            // KR: Build database
            .build()
    }

    // KR: function to find a user by id
    override suspend fun findUserById(user_name: String): User {
        return db.userDao().findUserById(user_name)
    }

    // KR: function to update a user in database
    override suspend fun updateUser(user: User) {
        db.userDao().update(user)
    }

    // KR: function to delete a user from database
    override suspend fun deleteUser(user: User) {
        db.userDao().delete(user)
    }

    // KR:  function to get a list of users from database
    override suspend fun getUsers(): List<User> {
        return db.userDao().getUsers()
    }

    // KR: function to insert a user into the database
    override suspend fun insertUser(user: User) {
        db.userDao().insert(user)
    }


}