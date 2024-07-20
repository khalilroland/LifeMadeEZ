package com.example.plannerproject

import androidx.compose.runtime.State

// KR: Interface for dialog
//KR: will go more indebt in the functions in the actual screen
interface IModal {
    val cdDialogue: State<Boolean>
    fun show(onConfirm: () -> Unit)
    fun onCD()
    // KR: Dismisses dialog
    fun dismiss()

}