@file:OptIn(ExperimentalMaterial3Api::class)

package me.androidbox.presentation.survey

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
    Box(modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.surveybackground),
            contentScale = ContentScale.FillWidth,
            contentDescription = null
        )
        Scaffold(
            modifier = modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    ),
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

            Column(
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .padding(it)
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 32.dp
                    )
            ) {
                Text(
                    text = stringResource(R.string.working_from_check_in),
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2)

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = stringResource(R.string.subtitle),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Thin,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(color = Color.Transparent),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    ActionButton(
                        modifier = Modifier
                            .width(140.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        label = "Start Survey",
                        fontSize = 16.sp,
                        onButtonClicked = {
                            /** Navigate to next screen */
                        }
                    )
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