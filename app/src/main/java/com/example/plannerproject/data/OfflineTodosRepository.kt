package com.example.plannerproject.data

import android.app.Application
import androidx.room.Room

class OfflineTodosRepository(app: Application) : TodosRepository{

    // MA: We Declare this private val to hold the TodoDatabase instance
    private val db: TodoDatabase

    // MA: Here we Initialize the TodoDatabase in the constructor using Room's databaseBuilder
    init {
        // MA: create the database
        db = Room.databaseBuilder(app, TodoDatabase::class.java, "todo.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    // MA: Implement the getTodos()
    // which returns todos from TodosRepository interface
    override suspend fun getTodos(): List<Todo> {
        // MA: Retrieve and return the list of todos from the TodoDao
        return db.todoDao().getTodos()
    }

    // MA: Implement the insertTodo() function from TodosRepository interface
    override suspend fun insertTodo(todo: Todo) {
        // MA: Insert a new todo into the TodoDao
        db.todoDao().insert(todo)
    }

    // MA: Implement the deleteTodo() function
    // which is responsible for deleting
    override suspend fun deleteTodo(todo: Todo) {
        // MA: Delete the specified todo from the TodoDao
        db.todoDao().delete(todo)
    }

    // MA: Implement the update() function
    // which updates the user
    override suspend fun update(todo: Todo) {
        db.todoDao().update(todo)
    }

}