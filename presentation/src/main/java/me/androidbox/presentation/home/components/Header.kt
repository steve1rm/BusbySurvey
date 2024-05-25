package me.androidbox.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.androidbox.presentation.R
import me.androidbox.presentation.ui.theme.BusbyNimbleSurveyTheme

@Composable
fun Header(
    modifier: Modifier = Modifier,
    profileImageClicked: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Monday, June 15",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Today",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Image(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .clickable(onClick = profileImageClicked),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.steverace),
                contentDescription = null
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewHeader() {
    BusbyNimbleSurveyTheme {
        Header(
            profileImageClicked = {}
        )
    }
}