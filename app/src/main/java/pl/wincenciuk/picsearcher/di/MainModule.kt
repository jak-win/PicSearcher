package pl.wincenciuk.picsearcher.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.wincenciuk.picsearcher.data.repository.ImageRepository
import pl.wincenciuk.picsearcher.data.repository.ImageRepositoryImpl
import pl.wincenciuk.picsearcher.data.service.ApiService
import pl.wincenciuk.picsearcher.presentation.viewmodel.ImageViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = module {
    single {
//        RetrofitInstance.getApiService()
        Retrofit.Builder()
            .baseUrl("https://pixabay.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    single<ImageRepository> {
        ImageRepositoryImpl(get())
    }

    viewModel {
        ImageViewModel(get())
    }
}