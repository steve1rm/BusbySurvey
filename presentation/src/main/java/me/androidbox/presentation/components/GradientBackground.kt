package me.androidbox.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import me.androidbox.presentation.R
import me.androidbox.presentation.ui.theme.BusbyNimbleSurveyTheme

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    hasToolbar: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {

    Box(modifier = modifier
        .fillMaxSize()) {

        Image(
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.signinbackground), contentDescription = null)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    if (hasToolbar) {
                        Modifier
                    } else {
                        Modifier.systemBarsPadding()
                    }
                )
        ) {
            content()
        }
    }
}

@Composable
@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
fun PreviewGradientBackground() {
    BusbyNimbleSurveyTheme {
        GradientBackground(
            modifier = Modifier,
            hasToolbar = true
        ) {

        }
    }
}