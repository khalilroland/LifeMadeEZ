package com.example.plannerproject

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
// KR: Function thats the UI for the items in the Friends list screen
fun UserDetail(
    // KR: User object
    user: User
) {
    // KR: State to track state of Checkbox
    val checked = remember { mutableStateOf(false) }

    // KR: OutlinedCard with colors to match the theme of the friend list screen
    //KR: made card red, border white, and text white
    OutlinedCard(
        colors = CardDefaults.cardColors(containerColor = Color.Red, contentColor = Color.White),
        border = BorderStroke(3.dp, Color.Black),
        //KR: Modifier for padding
        modifier = Modifier
            .padding(15.dp, 10.dp, 15.dp, 10.dp),
    ) {
        // KR: Row used for positioning
        Row(
            // KR: Modifier for padding
            modifier = Modifier.padding(15.dp),
            // KR: Alignment
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            // KR: Checkbox for deletion purposes
            // KR: used colors to match the theme of the cards
            Checkbox(
                checked = checked.value,
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.White,
                    uncheckedColor = Color.White,
                    checkmarkColor = Color.Red
                ),
                onCheckedChange = { isChecked ->
                    // KR: Updates user items isSelected status when checkbox state changes
                    user.isSelected = isChecked
                    // KR: Updates checked state
                    checked.value = isChecked
                },
            )
            // KR: Text with style and color to match theme
            Text(text = user.userName, fontSize = 30.sp, fontWeight = FontWeight.Bold)
            // KR: Row for displaying friends points
            Row(
                // KR: Modifier for max width
                modifier = Modifier.fillMaxWidth(),
                // KR: Alignment
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                // KR: Text with style and colore to display friends score
                //KR: and make it match the theme
                Text(text = "${user.score} ptz", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}