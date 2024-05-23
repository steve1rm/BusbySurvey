package me.androidbox.busbynimblesurvey.authentication.login

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.androidbox.busbynimblesurvey.R
import me.androidbox.busbynimblesurvey.components.ActionButton
import me.androidbox.busbynimblesurvey.components.EmailTextField
import me.androidbox.busbynimblesurvey.components.GradientBackground
import me.androidbox.busbynimblesurvey.components.PasswordTextField
import me.androidbox.busbynimblesurvey.ui.theme.BusbyNimbleSurveyTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    modifier: Modifier,
    loginState: LoginState,
    onLoginAction: (LoginAction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.nimblelogo),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(32.dp))

        EmailTextField(
            state = loginState.email,
            hint = stringResource(R.string.email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordTextField(
            state = loginState.password,
            hint = stringResource(R.string.password),
            onForgotPassword = {
                onLoginAction(LoginAction.OnForgotPassword)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ActionButton(
            label = stringResource(R.string.login),
            onButtonClicked = {
                onLoginAction(LoginAction.OnLoginClicked)
            })
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
fun PreviewLoginScreen() {
    BusbyNimbleSurveyTheme {
        GradientBackground(
            enableOverlay = true
        ) {
            LoginScreen(
                modifier = Modifier,
                loginState = LoginState(),
                onLoginAction = {}
            )
        }
    }
}

