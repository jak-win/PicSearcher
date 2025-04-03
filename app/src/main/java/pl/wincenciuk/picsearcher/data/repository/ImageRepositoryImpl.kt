package pl.wincenciuk.picsearcher.data.repository

import pl.wincenciuk.picsearcher.data.model.Hit
import pl.wincenciuk.picsearcher.data.service.ApiService
import pl.wincenciuk.picsearcher.utils.Constants

class ImageRepositoryImpl(private val apiService: ApiService) : ImageRepository {
    override suspend fun getImages(query: String): Result<List<Hit>> {
        return try {
            val response = apiService.getImages(query, Constants.API_KEY, "photo")
            Result.success(response.hits)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}