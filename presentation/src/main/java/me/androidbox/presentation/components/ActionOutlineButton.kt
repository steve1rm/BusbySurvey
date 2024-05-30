package me.androidbox.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.androidbox.presentation.R
import me.androidbox.presentation.ui.theme.BusbyNimbleSurveyTheme

@Composable
fun ActionOutlineButton(
    text: String,
    isLoading: Boolean,
    fontSize: TextUnit = 24.sp,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    onClicked: () -> Unit
) {
    OutlinedButton(
        modifier = modifier
            .height(IntrinsicSize.Min),
        /** Sets the height to the minimum height of one of its children */
        enabled = isEnabled,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        shape = CircleShape.copy(CornerSize(100f)),
        onClick = {
            onClicked()
        }) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center) {

            /** Using alpha to hide or show, which is the same as invisible or visible,
             *  so will retain the height of the button */
            CircularProgressIndicator(
                modifier = Modifier
                    .size(16.dp)
                    .alpha(if (isLoading) 1f else 0f),
                strokeWidth = 1.6.dp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                modifier = Modifier.alpha(if(isLoading) 0f else 1f),
                text = text,
                fontSize = fontSize,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
fun PreviewBusbyRunnerOutlineActionButton() {
    BusbyNimbleSurveyTheme {
        ActionOutlineButton(
            text = stringResource(id = R.string.reset),
            isLoading = false
        ) {
        }
    }
}
