package pl.wincenciuk.picsearcher.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.wincenciuk.picsearcher.data.model.Hit
import pl.wincenciuk.picsearcher.data.model.PixabayResponse
import pl.wincenciuk.picsearcher.data.repository.ImageRepository

class ImageViewModel(private val repository: ImageRepository) : ViewModel() {
    private val _imagesData = MutableStateFlow<List<PixabayResponse>>(emptyList())
    val imagesData: Flow<List<PixabayResponse>> = _imagesData.asStateFlow()

    private val _selectedItem = MutableStateFlow<Hit?>(null)
    val selectedItem: Flow<Hit?> = _selectedItem.asStateFlow()

    fun getImages(query: String) = viewModelScope.launch(Dispatchers.IO) {
        val response = repository.getImages(query)
        response.data?.hits?.let {
            _imagesData.value = listOf(PixabayResponse(hits = it))
        }
        Log.d("ViewModel", response.data.toString())
    }

    fun setSelectedItem(item: Hit) {
        _selectedItem.value = item
    }

    init {
        viewModelScope.launch {
            getImages("fruits")
        }
    }
}