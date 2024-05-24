package me.androidbox.busbynimblesurvey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.launch
import me.androidbox.busbynimblesurvey.navigation.LoginScreenRoot
import me.androidbox.busbynimblesurvey.navigation.ResetScreenRoute
import me.androidbox.busbynimblesurvey.ui.theme.BusbyNimbleSurveyTheme
import me.androidbox.domain.authorization.usecases.FetchTokenAuthorizationUseCase
import me.androidbox.domain.authorization.usecases.ResetPasswordUseCase
import me.androidbox.presentation.NotificationHandler
import me.androidbox.presentation.components.GradientBackground
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : ComponentActivity() {

    val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

   //     val registerUseCase by inject<RegisterUseCase>()
    //    val loginUseCase by inject<LoginUseCase>()*/
        val resetPasswordUseCase by inject<ResetPasswordUseCase>()
        val fetchTokenAuthorizationUseCase by inject<FetchTokenAuthorizationUseCase>()

        println(BuildConfig.CLIENT_KEY)
        println(BuildConfig.CLIENT_SECRET)

   /*     val notification = NotificationHandler(this@MainActivity)
        notification.start(MainActivity::class.java)
*/
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.mainState.isCheckingAuthorization
            }
        }

        setContent {
            BusbyNimbleSurveyTheme {
                val scope = rememberCoroutineScope()

                GradientBackground {
                    if(mainViewModel.mainState.isLoggedIn) {
                        Navigator(screen = ResetScreenRoute)
                    }
                    else {
                        Navigator(screen = LoginScreenRoot)
                    }
                }

                LaunchedEffect(key1 = true) {
                    scope.launch {
                     //   val result = registerUseCase.execute(RegisterUserModel("", "", "", ""))
                     //   val result = loginUseCase.execute(LoginRequestModel("", "", "", "", ""))
                    //    val result = resetPasswordUseCase.execute()

                        val result = fetchTokenAuthorizationUseCase.execute()
                        Timber.d("API Response %s", result)

                    }
                }
            }
        }
    }
}
