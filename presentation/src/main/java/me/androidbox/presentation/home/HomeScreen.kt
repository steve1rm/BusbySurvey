@file:OptIn(ExperimentalFoundationApi::class)

package me.androidbox.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import me.androidbox.presentation.ui.theme.BusbyNimbleSurveyTheme

@Composable
fun HomeScreen(
    onHomeAction: (HomeAction) -> Unit,
    homeState: HomeState,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {
            homeState.homeItems.count()
        }
    )

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {

     /*   HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fill,
        ) {index ->*/

            Box(modifier = Modifier.fillMaxSize()) {
/*                KamelImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    resource = asyncPainterResource(
                        data = homeState.homeItems[index].imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )*/

                Column(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "This is the title")

                        //       Text(text = homeState.homeItems[index].description)
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        //    Text(text = homeState.homeItems[index].title)

                        Text(text = "This is the description")
                    }
                }
            }
//        }

        if(homeState.isLoading) {
            CircularProgressIndicator(
                modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHomeScreen() {
    BusbyNimbleSurveyTheme {
        HomeScreen(
            onHomeAction = {},
            homeState = HomeState()
        )
    }
}