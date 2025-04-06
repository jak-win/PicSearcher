package pl.wincenciuk.picsearcher.data.repository

import pl.wincenciuk.picsearcher.data.model.PixabayResponse
import pl.wincenciuk.picsearcher.utils.Resource

interface ImageRepository {
    suspend fun getImages(query: String): Resource<PixabayResponse>
}