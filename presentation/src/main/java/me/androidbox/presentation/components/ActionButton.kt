package me.androidbox.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    label: String,
    fontSize: TextUnit = 24.sp,
    showLoading: Boolean = false,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    onButtonClicked: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        onClick = {
            onButtonClicked()
        },
        colors = colors,
        content = {
            if(showLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            else {
                Text(text = label,
                    style = LocalTextStyle.current.copy(
                        color = Color.Black,
                        fontSize = fontSize,
                        fontWeight = FontWeight.SemiBold
                    ))
            }
        }
    )
}


@Composable
@Preview(
    showBackground = true,
    showSystemUi = true)
@PreviewLightDark
fun PreviewActionButtonLoading() {
    ActionButton(
        modifier = Modifier.fillMaxWidth(),
        label = "Login",
        showLoading = true,
        onButtonClicked = {}
    )
}


@Composable
@Preview(
    showBackground = true,
    showSystemUi = true)
@PreviewLightDark
fun PreviewActionButton() {
    ActionButton(
        modifier = Modifier.fillMaxWidth(),
        label = "Login",
        showLoading = false,
        onButtonClicked = {}
    )
}