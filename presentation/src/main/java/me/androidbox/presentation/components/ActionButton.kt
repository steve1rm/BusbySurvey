package me.androidbox.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    label: String,
    showLoading: Boolean = false,
    onButtonClicked: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(brush = Brush.linearGradient(
                colors = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.tertiary)
            ),
                shape = RoundedCornerShape(16.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
        ),
        onClick = {
            onButtonClicked()
        },
        content = {
            if(showLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            else {
                Text(text = label,
                    style = LocalTextStyle.current.copy(
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
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