package pl.wincenciuk.picsearcher.data.repository

import pl.wincenciuk.picsearcher.data.model.Hit

interface ImageRepository {
    suspend fun getImages(query: String): Result<List<Hit>>
}