package pl.wincenciuk.picsearcher.data.repository

import pl.wincenciuk.picsearcher.data.model.PixabayResponse
import pl.wincenciuk.picsearcher.data.service.ApiService
import pl.wincenciuk.picsearcher.utils.Constants
import pl.wincenciuk.picsearcher.utils.Resource

class ImageRepositoryImpl(private val apiService: ApiService) : ImageRepository {

    override suspend fun getImages(query: String): Resource<PixabayResponse> {
        return try {
            val response = apiService.getImages(query, Constants.API_KEY, "photo")
            Resource.Success(data = response)
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}

