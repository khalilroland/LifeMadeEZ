package com.example.plannerproject


import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDialogue(
    modalViewModel: IModal,
) {
    // variables required for ModalBottomSheet
    // used documentation
    val sState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val show by modalViewModel.cdDialogue
    if (show) {
        // Here we are using ModalBottomSheet to achieve
        // the bottom modal which gives us the ability to confirm
        // befor we delete
        ModalBottomSheet(
            onDismissRequest = {
                modalViewModel.dismiss()
            },
            sheetState = sState
        ) {
            // Sheet content
            Row {
                // in this row we have the trash icon again
                // to confirm wether the user would like to delete
                // or no
                IconButton(onClick = {
                    modalViewModel.onCD()
                    scope.launch { sState.hide() }.invokeOnCompletion {
                        if (!sState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                }) {
                    // we use the Icons library to import the delete
                    // icon
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Delete Button",
                        tint = Color.Black
                    )
                }
                Text("Are you sure you are done with all tasks")
            }
        }
    }
}

