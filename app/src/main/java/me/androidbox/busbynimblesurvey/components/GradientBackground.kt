package me.androidbox.busbynimblesurvey.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.androidbox.busbynimblesurvey.R
import me.androidbox.busbynimblesurvey.ui.theme.BusbyNimbleSurveyTheme

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    enableOverlay: Boolean = false,
    hasToolbar: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {

    Box(modifier = modifier
        .fillMaxSize()) {

        Image(
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = 32.dp, edgeTreatment = BlurredEdgeTreatment.Rectangle),
            painter = painterResource(id = R.drawable.splash), contentDescription = null)

        if(enableOverlay) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(brush = Brush.verticalGradient(
                        listOf(Color.Transparent, Color.Black),
                    )
            ))
        }

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
            enableOverlay = true,
            hasToolbar = true
        ) {

        }
    }
}