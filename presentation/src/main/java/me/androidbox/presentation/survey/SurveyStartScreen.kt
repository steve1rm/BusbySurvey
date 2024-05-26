@file:OptIn(ExperimentalMaterial3Api::class)

package me.androidbox.presentation.survey

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.androidbox.presentation.R
import me.androidbox.presentation.components.ActionButton
import me.androidbox.presentation.ui.theme.BusbyNimbleSurveyTheme

@Composable
fun SurveyStartScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {

        Scaffold(
            modifier = modifier,
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        Icon(
                            modifier = Modifier.clickable {
                                onBackPressed()
                            },
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Go Back",
                            tint = Color.White
                        )
                    })
            }
        ) {

            Box(modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.surveybackground),
                    contentDescription = null
                )


            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 20.dp,
                        bottom = 32.dp
                    )
            ) {
                Text(text = "Working from Check-In", color = Color.White)

                Text(
                    text = "We would like to know how you feel about our work from home (WFH) experience",
                    color = Color.White
                )

                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    ActionButton(
                        modifier = Modifier
                            .width(140.dp)
                            .background(color = Color.Transparent, shape = RoundedCornerShape(4.dp)),
                        label = "Start Survey",
                        fontSize = 16.sp
                    ) {

                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewSurveyScreen() {
    BusbyNimbleSurveyTheme {
        SurveyStartScreen(
            onBackPressed = {}
        )
    }
}