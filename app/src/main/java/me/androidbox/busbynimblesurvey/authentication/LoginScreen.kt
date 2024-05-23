package me.androidbox.busbynimblesurvey.authentication

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import me.androidbox.busbynimblesurvey.components.GradientBackground
import me.androidbox.busbynimblesurvey.ui.theme.BusbyNimbleSurveyTheme

@Composable
fun LoginScreen() {

}

@Composable
@Preview(showBackground = true)
fun PreviewLoginScreen() {
    BusbyNimbleSurveyTheme {
        GradientBackground(
            enableOverlay = true
        ) {
            LoginScreen()
        }
    }
}

