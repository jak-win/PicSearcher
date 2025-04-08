package pl.wincenciuk.picsearcher

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pl.wincenciuk.picsearcher.data.repository.ImageRepositoryMock
import pl.wincenciuk.picsearcher.presentation.screens.MainScreen
import pl.wincenciuk.picsearcher.presentation.viewmodel.ImageViewModel

@RunWith(AndroidJUnit4::class)
class MainScreenInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testMainScreenDisplaysSearchText() {
        val repository = ImageRepositoryMock()
        val viewModel = ImageViewModel(repository)
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        composeTestRule.setContent {
            MainScreen(navController, viewModel)
        }

        composeTestRule.onNodeWithTag("searchText")
            .assertExists("Search text field should exist")
    }

    @Test
    fun testImageItemCardClickOpensDialog() {
        val repository = ImageRepositoryMock()
        val viewModel = ImageViewModel(repository)
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        composeTestRule.setContent {
            MainScreen(navController, viewModel)
        }

        composeTestRule.onNodeWithTag("imageItemCard_1")
            .performClick()

        composeTestRule.onNodeWithTag("confirmationDialog")
            .assertExists("Dialog should appear after clicking an image item")
    }

    @Test
    fun testTextFieldInput() {
        val repository = ImageRepositoryMock()
        val viewModel = ImageViewModel(repository)
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        composeTestRule.setContent {
            MainScreen(navController, viewModel)
        }

        val testInput = "flower"

        composeTestRule.onNodeWithTag("searchTextField")
            .performTextClearance()

        composeTestRule.onNodeWithTag("searchTextField")
            .performTextInput(testInput)

        composeTestRule.onNodeWithTag("searchTextField")
            .assertTextEquals(testInput)
    }
}