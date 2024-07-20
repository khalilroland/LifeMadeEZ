package com.example.plannerproject.ui.todolist

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plannerproject.data.OfflineTodosRepository
import com.example.plannerproject.data.Todo
import com.example.plannerproject.data.TodosRepository
import kotlinx.coroutines.launch
import java.util.UUID

class TodoListViewModel(app: Application) : AndroidViewModel(app) {
    private val _todos: MutableState<List<Todo>> = mutableStateOf(listOf())
    val todos: State<List<Todo>> = _todos

    private val _waiting: MutableState<Boolean>
    val waiting: State<Boolean>

    private val temp = Todo(UUID.randomUUID(),"test",13,false)


    private val _repository: TodosRepository = OfflineTodosRepository(getApplication())

    init {
        viewModelScope.launch {
            _todos.value = _repository.getTodos()
        }


        _waiting = mutableStateOf(false)
        waiting = _waiting
    }


    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            _waiting.value = true
            _repository.insertTodo(todo)
            _todos.value = _repository.getTodos()
            _waiting.value = false
        }
    }

    suspend fun deleteTodo(todo: Todo) {
            _repository.deleteTodo(todo)
            _todos.value = _repository.getTodos()
    }

}