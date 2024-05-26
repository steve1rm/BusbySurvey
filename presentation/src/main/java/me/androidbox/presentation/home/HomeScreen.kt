@file:OptIn(ExperimentalFoundationApi::class)

package me.androidbox.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch
import me.androidbox.presentation.home.components.Footer
import me.androidbox.presentation.home.components.Header
import me.androidbox.presentation.ui.theme.BusbyNimbleSurveyTheme
import timber.log.Timber

@Composable
fun HomeScreen(
    onForwardButtonClicked: () -> Unit,
    homeState: HomeState,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {
            homeState.homeItems.count()
        }
    )

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {

        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fill,
        ) { index ->

            Box(modifier = Modifier.fillMaxSize()) {
                KamelImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    resource = asyncPainterResource(
                        data = homeState.homeItems[index].imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    onLoading = {
                        CircularProgressIndicator()
                    }
                )

                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 20.dp,
                        bottom = 32.dp
                    )) {
                    Box(modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopStart) {

                        Header(
                            header = "Saturday, December 25",
                            subHeader = "Today",
                            profileImageClicked = {}
                        )
                    }

                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomStart) {
                        Footer( title = "Working from home Check-In",
                            description = "We would like to know you feel about our work from home",
                            onNextPageClicked = {
                                if(pagerState.currentPage == pagerState.pageCount - 1) {
                                    Timber.d("Go to the survey screen")
                                    onForwardButtonClicked()
                                }
                                else {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                                    }
                                }
                            })
                    }
                }
            }
        }

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
            onForwardButtonClicked = {},
            homeState = HomeState()
        )
    }
}