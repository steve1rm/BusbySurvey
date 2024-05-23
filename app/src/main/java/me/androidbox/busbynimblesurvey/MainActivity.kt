package me.androidbox.busbynimblesurvey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import me.androidbox.busbynimblesurvey.ui.theme.BusbyNimbleSurveyTheme
import me.androidbox.domain.authorization.models.RegisterUserModel
import me.androidbox.domain.authorization.usecases.RegisterUseCase
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val registerUseCase by inject<RegisterUseCase>()

        println(BuildConfig.CLIENT_KEY)
        println(BuildConfig.CLIENT_SECRET)

        setContent {
            BusbyNimbleSurveyTheme {
                val scope = rememberCoroutineScope()

                LaunchedEffect(key1 = true) {
                    scope.launch {
                        registerUseCase.execute(RegisterUserModel("", "", "", ""))
                    }
                }
            }
        }
    }
}
