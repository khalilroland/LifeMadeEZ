package com.example.plannerproject

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
// KR: Function thats the UI for the items in the leaderboard
fun LeaderboardDetail(
    position: Int,
    user: User
) {
    // KR: This val textcolor determines text color based on the leaderboard position
    //KR: top 3 are white to highlight them
    val textColor = if (position <= 3) Color.White else Color.Red
    // KR: outlined card with styling for items in leaderboard
    // KR: ment to match leaderboard theme
    OutlinedCard(
        // KR: card colors which make the card black and text ed
        colors = CardDefaults.cardColors(containerColor = Color.Black, contentColor = Color.Red),
        // KR: Border with white color to make cards standout
        border = BorderStroke(3.dp, Color.White),
        // KR: Modifier for padding
        modifier = Modifier
            .padding(15.dp, 10.dp, 15.dp, 10.dp),
        // KR: Whats in the card:
    ) {
        // KR: Row used for positioning whats in the card
        Row(
            // KR: Modifier for padding
            modifier = Modifier.padding(15.dp),
            // KR: Alignment
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            // KR: Displays the Users position and username
            //KR: uses colors to match theme, implements text color
            //KR: also styles text with fontsize,weight
            Text(
                text = "$position. " + user.userName,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )

            // KR: Row for displaying score
            Row(
                // KR: Modifier for filling max width
                modifier = Modifier.fillMaxWidth(),
                // KR: Alignment
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                // KR: Displays the user score with color to match theme
                //KR: also styles text with fontsize,weight
                Text(
                    text = "${user.score} ptz",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
            }
        }
    }
}