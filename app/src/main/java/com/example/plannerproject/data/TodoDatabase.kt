package com.example.plannerproject.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import com.example.plannerproject.User

// Task Dao that uses the room database dependency
// to add the CRUD operations
@Dao
interface TodoDao {
    // MA: suspend function to insert a Task
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo: Todo)

    // MA: suspend function to update a Task
    @Update
    suspend fun update(todo: Todo)

    // MA: suspend function to delete a Task
    @Delete
    suspend fun delete(todo: Todo)

    // MA: suspend function to retrieve all tasks
    @Query("SELECT * from todos")
    suspend fun getTodos(): List<Todo>
}

@Dao
interface UserDao {
    // MA: suspend function to insert a user
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    // MA: suspend function to update user
    @Update
    suspend fun update(user: User)

    // MA: suspend function to delete user
    @Delete
    suspend fun delete(user: User)

    // MA: suspend function to get all users
    @Query("SELECT * from users")
    suspend fun getUsers(): List<User>

    // MA: suspend function to get user by id
    @Query("SELECT * from users WHERE users.userName=:userName")
    suspend fun findUserById(userName: String): User

}

// The following code defines an AppDatabase class to
// hold the database. AppDatabase defines the database
// configurations. We added both the task and user entities

// MA: Define our RoomDatabase with the user and task entity
@Database(entities = [Todo::class,User::class], version = 18, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {
    // MA: Abstract functions to get instances of TodoDao and UserDao
    abstract fun todoDao(): TodoDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var Instance: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TodoDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}