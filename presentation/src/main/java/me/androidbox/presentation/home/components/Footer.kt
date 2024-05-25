package me.androidbox.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.androidbox.presentation.ui.theme.BusbyNimbleSurveyTheme

@Composable
fun Footer(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    profileImageClicked: () -> Unit) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        /** Indicator dots */

        Text(
            text = title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            maxLines = 2,
            lineHeight = TextUnit(32F, TextUnitType.Sp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = description,
                fontSize = 16.sp,
                fontWeight = FontWeight.Thin,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(
                        color = Color.White
                    )
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(28.dp)
                        .clickable(onClick = profileImageClicked),
                    imageVector = Icons.AutoMirrored.Default.ArrowForward,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewFooter() {
    BusbyNimbleSurveyTheme {
        Footer(
            title = "Working from home Check-In",
            description = "We would like to know you feel about our work from home",
            profileImageClicked = {}
        )
    }
}