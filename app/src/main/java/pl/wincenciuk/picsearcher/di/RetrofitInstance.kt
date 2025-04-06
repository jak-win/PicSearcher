package pl.wincenciuk.picsearcher.di

import pl.wincenciuk.picsearcher.data.service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pixabay.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService(): ApiService {
        return getInstance().create(ApiService::class.java)
    }
}