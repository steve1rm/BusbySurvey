@file:OptIn(ExperimentalFoundationApi::class)

package me.androidbox.presentation.authentication.reset

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import me.androidbox.presentation.ui.theme.BusbyNimbleSurveyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordScreen(
    resetPasswordState: ResetPasswordState,
    modifier: Modifier = Modifier,
    onResetPasswordAction: (ResetPasswordAction) -> Unit,
    onBackPressed: () -> Unit,
    onResetPasswordSuccess: (message: String) -> Unit
) {

    LaunchedEffect(key1 = resetPasswordState.isResetPasswordSuccess) {
        if(resetPasswordState.isResetPasswordSuccess && resetPasswordState.message.isNotBlank()) {
            onResetPasswordSuccess(resetPasswordState.message)
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            onBackPressed()
                        },
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Go Back"
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(32.dp))

            Image(
                modifier = Modifier.fillMaxWidth(),
                imageVector = ImageVector.vectorResource(id = R.drawable.nimblelogo),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "Enter your email for instructions for resetting your password")

            Spacer(modifier = Modifier.height(32.dp))

            EmailTextField(
                state = resetPasswordState.email,
                hint = stringResource(R.string.email)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ActionButton(
                label = stringResource(R.string.reset),
                onButtonClicked = {
                    onResetPasswordAction(ResetPasswordAction.OnPasswordResetClicked)
                })
        }
    }
}

@Composable
@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
fun PreviewResetScreen() {
    BusbyNimbleSurveyTheme {
        GradientBackground {
            ResetPasswordScreen(
                resetPasswordState = ResetPasswordState(),
                onResetPasswordAction = {},
                onBackPressed = {},
                onResetPasswordSuccess = {
                }
            )
        }
    }
}