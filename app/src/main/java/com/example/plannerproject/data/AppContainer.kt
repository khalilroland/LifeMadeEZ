//package com.example.plannerproject.data
//
//import android.content.Context
//
///**
// * App container for Dependency injection.
// */
//interface AppContainer {
//    val todosRepository: TodosRepository
//}
//
///**
// * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
// */
//class AppDataContainer(private val context: Context) : AppContainer {
//
//    override val todosRepository: TodosRepository by lazy {
//        OfflineTodosRepository(TodoDatabase.getDatabase(context).todoDao())
//    }
//}