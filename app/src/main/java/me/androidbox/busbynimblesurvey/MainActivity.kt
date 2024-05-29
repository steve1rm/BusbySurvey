package me.androidbox.busbynimblesurvey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cafe.adriel.voyager.navigator.Navigator
import me.androidbox.busbynimblesurvey.navigation.HomeScreenRoute
import me.androidbox.busbynimblesurvey.navigation.LoginScreenRoute
import me.androidbox.busbynimblesurvey.ui.theme.BusbyNimbleSurveyTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.mainState.isCheckingAuthorization
            }
        }

        setContent {
            BusbyNimbleSurveyTheme {
                if(mainViewModel.mainState.isLoggedIn) {
                    Navigator(screen = HomeScreenRoute)
                }
                else {
                    Navigator(screen = LoginScreenRoute)
                }
            }
        }
    }
}
