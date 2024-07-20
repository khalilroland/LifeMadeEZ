package com.example.plannerproject.data

import kotlinx.coroutines.flow.Flow

// MA: our repository interface with all
// crud operations
interface TodosRepository {
    suspend fun getTodos(): List<Todo>
    suspend fun insertTodo(todo: Todo)
    suspend fun deleteTodo(todo: Todo)
    suspend fun update(todo: Todo)
}