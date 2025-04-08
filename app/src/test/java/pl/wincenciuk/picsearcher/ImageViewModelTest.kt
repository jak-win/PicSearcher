package pl.wincenciuk.picsearcher

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import pl.wincenciuk.picsearcher.data.repository.ImageRepositoryMock
import pl.wincenciuk.picsearcher.presentation.viewmodel.ImageViewModel

class ImageViewModelTest {

    private lateinit var viewModel: ImageViewModel
    private val repository = ImageRepositoryMock()

    @Before
    fun setUp() {
        viewModel = ImageViewModel(repository)
    }

    @Test
    fun testGetImages() = runTest {
        val query = "flower"
        viewModel.getImages(query)
        val images = runBlocking { viewModel.imagesData.first() }
        assert(images.isNotEmpty()) { "Image list should not be empty" }
    }
}