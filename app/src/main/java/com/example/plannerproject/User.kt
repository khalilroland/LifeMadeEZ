package com.example.plannerproject

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.util.UUID

// KR: class for users
// KR: Entity represents the User table in the database
@Entity(tableName = "users")
class User(
    // KR: Primary key for the User
    // KR: uses random UUID
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    // KR: Column for username
    @ColumnInfo(name = "userName")
    @SerializedName("userName")
    val userName: String,
    // KR: Column for  password
    @SerializedName("userPass")
    val password: String,
    // KR: Var for  score
    var score: Int,
    // KR: Var for showing if user is selected or not
    //KR: only really used for deletion purposes
    var isSelected: Boolean,
)