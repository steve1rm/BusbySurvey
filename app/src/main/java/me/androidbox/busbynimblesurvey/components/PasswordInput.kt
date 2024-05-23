package me.androidbox.busbynimblesurvey.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.BasicSecureTextField
import androidx.compose.foundation.text2.input.TextObfuscationMode
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    label: String,
    currentValue: String,
    leadingIcon: ImageVector = Icons.Default.Lock,
    focusRequester: FocusRequester? = null,
    onValueChange: (String) -> Unit,
    onForgotPassword: () -> Unit
) {

    var isPasswordVisbileState by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BasicSecureTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .focusRequester(focusRequester ?: FocusRequester())
                .background(
                    brush = Brush.linearGradient(
                        listOf(
                            MaterialTheme.colorScheme.secondaryContainer,
                            MaterialTheme.colorScheme.onSecondary
                        )
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .border(
                    width = 0.1.dp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    shape = RoundedCornerShape(8.dp)
                ),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onPrimaryContainer),
            value = currentValue,
            onValueChange = onValueChange,
            textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onPrimaryContainer, fontSize = 20.sp),
            decorator = { textFieldDecorator ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 16.dp),
                            text = "Password?",
                            style = LocalTextStyle.current.copy(
                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                                fontSize = 12.sp
                            ))

                        textFieldDecorator()
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier

                    ) {
                        TextButton(
                            onClick = onForgotPassword,
                            modifier = Modifier,
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(0.dp),
                                textAlign = TextAlign.End,
                                text = "Forgot?",
                                style = LocalTextStyle.current.copy(
                                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                                    fontSize = 12.sp
                                ))
                        }
                    }
                }
            },
            textObfuscationMode =
            if (isPasswordVisbileState) {
                TextObfuscationMode.Visible
            } else {
                TextObfuscationMode.Hidden
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewPasswordInput() {

    var textInput by remember {
        mutableStateOf("")
    }

    PasswordInput(
        modifier = Modifier.fillMaxWidth(),
        label = "Password",
        leadingIcon = Icons.Default.Lock,
        currentValue = textInput,
        onValueChange = {
            textInput = it
        },
        onForgotPassword = {}
    )
}
