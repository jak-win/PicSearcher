package pl.wincenciuk.picsearcher.data.repository

import pl.wincenciuk.picsearcher.data.local.ImageDao
import pl.wincenciuk.picsearcher.data.model.PixabayResponse
import pl.wincenciuk.picsearcher.data.service.ApiService
import pl.wincenciuk.picsearcher.utils.Constants
import pl.wincenciuk.picsearcher.utils.Resource

class ImageRepositoryImpl(
    private val apiService: ApiService,
    private val imageDao: ImageDao,
) : ImageRepository {

    override suspend fun getImages(query: String): Resource<PixabayResponse> {
        return try {
            val response = apiService.getImages(query, Constants.API_KEY, "photo")
            if (response.hits.isNotEmpty()) {
                imageDao.insertAll(response.hits)
            }
            Resource.Success(data = response)
        } catch (e: Exception) {
            val cachedImages = imageDao.getAllImages()
            if (cachedImages.isNotEmpty()) {
                Resource.Success(data = PixabayResponse(hits = cachedImages))
            } else {
                Resource.Error(e.message.toString())
            }
        }
    }
}

