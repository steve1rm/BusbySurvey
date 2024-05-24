package me.androidbox.busbynimblesurvey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import me.androidbox.busbynimblesurvey.ui.theme.BusbyNimbleSurveyTheme
import me.androidbox.presentation.authentication.login.LoginScreenRoot
import me.androidbox.presentation.components.GradientBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*
        val registerUseCase by inject<RegisterUseCase>()
        val loginUseCase by inject<LoginUseCase>()*/

    /*    println(BuildConfig.CLIENT_KEY)
        println(BuildConfig.CLIENT_SECRET)
*/
        setContent {
            BusbyNimbleSurveyTheme {
        //        val scope = rememberCoroutineScope()

                GradientBackground {
                    Navigator(screen = LoginScreenRoot)
                }
/*
                LaunchedEffect(key1 = true) {
                    scope.launch {
                     //   val result = registerUseCase.execute(RegisterUserModel("", "", "", ""))

                        val result = loginUseCase.execute(LoginRequestModel(
                            "", "", "", "", ""))

                        Timber.d("API Response %s", result)
                    }
                }*/
            }
        }
    }
}
