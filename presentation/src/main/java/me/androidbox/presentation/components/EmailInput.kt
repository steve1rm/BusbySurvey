package me.androidbox.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    label: String,
    currentValue: String,
    leadingIcon: ImageVector,
    keyboardActions: KeyboardActions,
    onEmailChange: (String) -> Unit) {

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BasicTextField2(
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(
                    brush = Brush.linearGradient(
                        listOf(MaterialTheme.colorScheme.secondaryContainer, MaterialTheme.colorScheme.onSecondary)
                    ),
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .border(
                    width = 0.1.dp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    shape = RoundedCornerShape(size = 8.dp)
                ),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onPrimaryContainer),
            value = currentValue,
            onValueChange = onEmailChange,
            lineLimits = TextFieldLineLimits.SingleLine,
            textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onPrimaryContainer, fontSize = 20.sp),
            keyboardActions = keyboardActions,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
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
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(32.dp),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                        textFieldDecorator()
                    }
                }
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewEmailInput() {
    EmailInput(
        modifier = Modifier.fillMaxWidth(),
        label = "Email",
        leadingIcon = Icons.Default.Email,
        currentValue = "steve@gmail.com",
        keyboardActions = KeyboardActions.Default,
        onEmailChange = {}
    )
}


