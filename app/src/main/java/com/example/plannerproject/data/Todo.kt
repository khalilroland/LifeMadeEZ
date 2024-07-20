package com.example.plannerproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "todos")
class Todo(
    // MA:the primary key for the Entity
    @PrimaryKey
    // MA: Initialize the ID with a randomly generated UUID
    val id: UUID = UUID.randomUUID(),
    // MA:store the task description
    val task: String,
    // MA:points associated with the task
    val points: Int,
    // MA:track whether the task is complete or not
    var isComplete: Boolean
)

