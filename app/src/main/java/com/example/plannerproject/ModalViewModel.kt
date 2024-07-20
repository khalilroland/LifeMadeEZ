package com.example.plannerproject

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ModalViewModel : ViewModel(), IModal {
    // MA: MutableState variable that represent the
    // visibility state of the modal, initially set to false
    private val cdBool: MutableState<Boolean> = mutableStateOf(false)
    // MA: Show the visibility state of the
    // modal as an immutable State variable
    override val cdDialogue: State<Boolean> = cdBool
    // MA: A function that represents
    // the action to be taken when the modal is dismissed
    private var cdBack: (() -> Unit)? = null


    // MA: Implementation of the onCD() function
    // when calling onCD() we invoke cdBack
    override fun onCD() {
        // MA: Check if a callback function is set,
        // invoke it, and then dismiss the modal
        if (cdBack != null) {
            cdBack?.invoke()
            dismiss()
        }
    }
    // MA: Implementation of the dismiss()
    // function which dismisses the modal
    // when clicking on screen
    override fun dismiss() {
        // MA: Set the visibility
        // state of the modal to false
        cdBool.value = false
    }

    // MA: Implementation of the show()
    // function which shows the modal
    override fun show(i: () -> Unit) {
        viewModelScope.launch {
            cdBool.value = true
            cdBack = i
        }
    }
}
