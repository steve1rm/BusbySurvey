package me.androidbox.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import kotlinx.coroutines.test.runTest
import me.androidbox.presentation.home.HomeItems
import me.androidbox.presentation.home.HomeScreen
import me.androidbox.presentation.home.HomeState
import org.junit.Rule
import org.junit.Test
import java.util.UUID

class HomeScreenTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun should_show_home_contents() {
        // Arrange
        rule.setContent {
            HomeScreen(
                homeState = HomeState(homeItems = createHomeItems()),
                onHomeAction = { },
                onForwardButtonClicked = { },
                onLogoutSuccess = { })
        }

        // Act & Assert
        rule.onNodeWithTag("home_pager").assertExists()
    }
}

private fun createHomeItems(): List<HomeItems> {
    return sequence<HomeItems> {
        HomeItems(
            title = UUID.randomUUID().toString(),
            description = UUID.randomUUID().toString(),
            imageUrl = UUID.randomUUID().toString()
        )
    }.take(5).toList()
}