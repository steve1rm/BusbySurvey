package me.androidbox.presentation.thankyou

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import me.androidbox.presentation.R
import me.androidbox.presentation.ui.theme.BusbyNimbleSurveyTheme

@Composable
fun ThankYouScreen(
    onTerminateScreen: () -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        delay(1_000)
        onTerminateScreen()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(60.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

     /*   Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.thankyou),
            contentDescription = null)
*/
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.thank_you),
            fontSize = 28.sp,
            lineHeight = TextUnit(40f, TextUnitType.Sp),
            fontWeight = FontWeight.Bold,
            color = Color.White)
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewThankYouScreen() {
    BusbyNimbleSurveyTheme {
        ThankYouScreen(
            onTerminateScreen = {}
        )
    }
}

