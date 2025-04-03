package pl.wincenciuk.picsearcher.data.service

import pl.wincenciuk.picsearcher.data.model.PixabayResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/")
    suspend fun getImages(
        @Query("q") query: String,
        @Query("key") apiKey: String,
        @Query("image_type") imageType: String,
    ): PixabayResponse
}