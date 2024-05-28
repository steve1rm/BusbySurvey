package me.androidbox.busbynimblesurvey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.launch
import me.androidbox.busbynimblesurvey.navigation.HomeScreenRoute
import me.androidbox.busbynimblesurvey.navigation.LoginScreenRoute
import me.androidbox.busbynimblesurvey.ui.theme.BusbyNimbleSurveyTheme
import me.androidbox.domain.authorization.models.RegisterUserModel
import me.androidbox.domain.authorization.usecases.RegisterUseCase
import me.androidbox.domain.survey.usecases.FetchSurveyListUseCase
import me.androidbox.presentation.components.GradientBackground
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    //    val registerUseCase by inject<RegisterUseCase>()
    //    val loginUseCase by inject<LoginUseCase>()*/
       // val resetPasswordUseCase by inject<ResetPasswordUseCase>()
      //  val fetchTokenAuthorizationUseCase by inject<FetchTokenAuthorizationUseCase>()
        val fetchSurveyListUseCase by inject<FetchSurveyListUseCase>()

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

                if(mainViewModel.mainState.isLoggedIn) {
                    Navigator(screen = HomeScreenRoute)
                }
                else {
                    GradientBackground {
                        Navigator(screen = LoginScreenRoute)
                    }
                }

                LaunchedEffect(key1 = true) {
                    scope.launch {
                        //   val result = registerUseCase.execute(RegisterUserModel("far@mail.com", "far", "1234567", "1234567"))
                        //   val result = loginUseCase.execute(LoginRequestModel("", "", "", "", ""))
                        //    val result = resetPasswordUseCase.execute()
                        //    val result = fetchTokenAuthorizationUseCase.execute()
                        // val result = fetchSurveyListUseCase.execute()

                        // Timber.d("API Response %s", result)
                    }
                }
            }
        }
    }
}
