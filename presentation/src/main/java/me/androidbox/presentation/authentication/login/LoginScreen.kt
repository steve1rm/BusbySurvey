package me.androidbox.presentation.authentication.login

import android.Manifest
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.androidbox.presentation.R
import me.androidbox.presentation.components.ActionButton
import me.androidbox.presentation.components.ActionOutlineButton
import me.androidbox.presentation.components.EmailTextField
import me.androidbox.presentation.components.GradientBackground
import me.androidbox.presentation.components.HomeDialog
import me.androidbox.presentation.components.PasswordTextField
import me.androidbox.presentation.home.HomeAction
import me.androidbox.presentation.ui.theme.BusbyNimbleSurveyTheme
import me.androidbox.presentation.utils.hasNoticationPermission
import me.androidbox.presentation.utils.shouldShowNoticationPermissionRationale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginState: LoginState,
    onLoginSuccess: () -> Unit,
    onForgotPassword: () -> Unit,
    onLoginAction: (LoginAction) -> Unit
) {

    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = loginState.isLoginSuccess, key2 = loginState.isLoggingIn) {
        if (loginState.isLoginSuccess) {
            keyboard?.hide()
            onLoginSuccess()
        }

        if(!loginState.isLoginSuccess && loginState.errorMessage.asString(context).isNotBlank()) {
            keyboard?.hide()
            Toast.makeText(context, loginState.errorMessage.asString(context), Toast.LENGTH_LONG)
                .show()

            onLoginAction(LoginAction.OnResetScreen)
        }

    }

    val permissionNotificationLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isPermissionAccepted ->
        /** User has accepted or declined the notification permission */
        val activity = context as ComponentActivity
        val shouldShowNotificationRationale = activity.shouldShowNoticationPermissionRationale()

        onLoginAction(LoginAction.SubmitNotificationPermissionInfo(
            acceptedNotificationPermission = isPermissionAccepted,
            showNotificationPermissionRationale = shouldShowNotificationRationale
        ))
    }

    LaunchedEffect(key1 = true) {
        val activity = context as ComponentActivity
        val showNotificationRationale = activity.shouldShowNoticationPermissionRationale()

        onLoginAction(
            LoginAction.SubmitNotificationPermissionInfo(
            acceptedNotificationPermission = context.hasNoticationPermission(),
            showNotificationPermissionRationale = showNotificationRationale
        ))

        if(!showNotificationRationale) {
            permissionNotificationLauncher.requestBusbySurveyPermission(context)
        }
    }

    GradientBackground {
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

            Spacer(modifier = Modifier.height(24.dp))

            PasswordTextField(
                state = loginState.password,
                hint = stringResource(R.string.password),
                onForgotPassword = {
                    onForgotPassword()
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            ActionButton(
                modifier = Modifier.background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp)
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                label = stringResource(R.string.login),
                onButtonClicked = {
                    keyboard?.hide()

                    onLoginAction(LoginAction.OnLoginClicked)
                },
                showLoading = loginState.isLoggingIn
            )
        }

        if(loginState.showNotificationRationale) {
            /** Normal dismissing not allowed for permissions user needs to click ok */
            HomeDialog(
                title = stringResource(R.string.permission_requested),
                onDismiss = { /** Normal dismissing not allowed for permissions user needs to click ok */},
                description = stringResource(R.string.notification_rationale),
                primaryButton = {
                    ActionOutlineButton(
                        text = stringResource(R.string.okay),
                        isLoading = false
                    ) {
                        onLoginAction(LoginAction.DismissRationaleDialog)
                        permissionNotificationLauncher.requestBusbySurveyPermission(context)
                    }
                }
            )
        }
    }
}


private fun ActivityResultLauncher<String>.requestBusbySurveyPermission(context: Context) {
    val hasNotificationPermission = context.hasNoticationPermission()

    if(Build.VERSION.SDK_INT >= 33 && !hasNotificationPermission) {
        launch(Manifest.permission.POST_NOTIFICATIONS)
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

