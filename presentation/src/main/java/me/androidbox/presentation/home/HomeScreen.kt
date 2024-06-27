@file:OptIn(ExperimentalFoundationApi::class)

package me.androidbox.presentation.home

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import me.androidbox.presentation.R
import me.androidbox.presentation.components.ActionButton
import me.androidbox.presentation.components.ActionOutlineButton
import me.androidbox.presentation.components.HomeDialog
import me.androidbox.presentation.home.components.Footer
import me.androidbox.presentation.home.components.Header
import me.androidbox.presentation.home.components.Indicators
import me.androidbox.presentation.ui.theme.BusbyNimbleSurveyTheme
import me.androidbox.presentation.utils.hasNoticationPermission
import me.androidbox.presentation.utils.shouldShowNoticationPermissionRationale

@Composable
fun HomeScreen(
    homeState: HomeState,
    onHomeAction: (action: HomeAction) -> Unit,
    onForwardButtonClicked: () -> Unit,
    onLogoutSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val permissionNotificationLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isPermissionAccepted ->
        /** User has accepted or declined the notification permission */
        val activity = context as ComponentActivity
        val shouldShowNotificationRationale = activity.shouldShowNoticationPermissionRationale()

        onHomeAction(HomeAction.SubmitNotificationPermissionInfo(
            acceptedNotificationPermission = isPermissionAccepted,
            showNotificationPermissionRationale = shouldShowNotificationRationale
        ))
    }

    LaunchedEffect(homeState.isSuccessLogout) {
        if(homeState.isSuccessLogout) {
            onLogoutSuccess()
        }
    }

    LaunchedEffect(key1 = true) {
        val activity = context as ComponentActivity
        val showNotificationRationale = activity.shouldShowNoticationPermissionRationale()

        onHomeAction(HomeAction.SubmitNotificationPermissionInfo(
            acceptedNotificationPermission = context.hasNoticationPermission(),
            showNotificationPermissionRationale = showNotificationRationale
        ))

        if(!showNotificationRationale) {
            permissionNotificationLauncher.requestBusbySurveyPermission(context)
        }
    }

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

        HorizontalPager(
            modifier = Modifier.testTag("home_pager"),
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
                            profileImageClicked = {
                                /** Logout */
                                onHomeAction(HomeAction.LogoutUser)
                            }
                        )
                    }

                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomStart) {

                        Column(modifier = Modifier.fillMaxWidth()) {
                            Indicators(pagerState)

                            Footer(
                                title = homeState.homeItems[index].title,
                                description = homeState.homeItems[index].description,
                                onNextPageClicked = {
                                    /** Clicking the arrow right will always go to the survey screen */
                                    onForwardButtonClicked()

                                  /*  if (pagerState.currentPage == pagerState.pageCount - 1) {
                                        Timber.d("Go to the survey screen")
                                        onForwardButtonClicked()
                                    } else {
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                                        }
                                    }*/
                                })
                        }
                    }
                }
            }
        }

        if(homeState.isLoading) {
            CircularProgressIndicator(
                modifier.align(Alignment.Center)
            )
        }

        if(homeState.showShowDialog) {
            HomeDialog(
                title = "Warning",
                onDismiss = {
                    onHomeAction(HomeAction.CancelLogout)
                },
                description = "You are about to logout",
                secondaryButton = {
                    ActionOutlineButton(
                        modifier = Modifier.weight(1f),
                        fontSize = 16.sp,
                        text = "Cancel",
                        isLoading = false ) {
                        onHomeAction(HomeAction.CancelLogout)
                    }
                },
                primaryButton = {
                    ActionButton(
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f),
                        label = "Continue") {
                        onHomeAction(HomeAction.ContinueLogout)
                    }
                }
            )
        }

        if(homeState.showNotificationRationale) {
            HomeDialog(
                title = stringResource(R.string.permission_requested),
                onDismiss = { /** Normal dismissing not allowed for permissions user needs to click ok */},
                description = stringResource(R.string.notification_rationale),
                primaryButton = {
                    ActionOutlineButton(
                        text = stringResource(R.string.okay),
                        isLoading = false
                    ) {
                        onHomeAction(HomeAction.DismissRationaleDialog)
                        permissionNotificationLauncher.requestBusbySurveyPermission(context)
                    }
                }
            )
        }
    }
}

private fun ActivityResultLauncher<String>.requestBusbySurveyPermission(context: Context) {
    val hasNotificationPermission = context.hasNoticationPermission()

    if(Build.VERSION.SDK_INT >= 33 && !hasNotificationPermission) {
        launch(Manifest.permission.POST_NOTIFICATIONS)
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHomeScreen() {
    BusbyNimbleSurveyTheme {
        HomeScreen(
            onForwardButtonClicked = {},
            homeState = HomeState(),
            onHomeAction = {},
            onLogoutSuccess = {}
        )
    }
}