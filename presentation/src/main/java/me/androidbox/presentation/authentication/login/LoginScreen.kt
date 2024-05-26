package me.androidbox.presentation.authentication.login

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.androidbox.presentation.R
import me.androidbox.presentation.components.ActionButton
import me.androidbox.presentation.components.EmailTextField
import me.androidbox.presentation.components.GradientBackground
import me.androidbox.presentation.components.PasswordTextField
import me.androidbox.presentation.ui.theme.BusbyNimbleSurveyTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginState: LoginState,
    onLoginSuccess: () -> Unit,
    onForgotPassword: () -> Unit,
    onLoginAction: (LoginAction) -> Unit
) {

    LaunchedEffect(key1 = loginState.isLoginSuccess) {
        if (loginState.isLoginSuccess) {
            onLoginSuccess()
        }
    }

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
                onForgotPassword()
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
        GradientBackground {
            LoginScreen(
                modifier = Modifier,
                loginState = LoginState(),
                onLoginAction = {},
                onLoginSuccess = {},
                onForgotPassword = {}
            )
        }
    }
}

