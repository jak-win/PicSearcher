package pl.wincenciuk.picsearcher.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.wincenciuk.picsearcher.data.local.ImageDatabase
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

    single {
        Room.databaseBuilder(
            androidContext(),
            ImageDatabase::class.java, "image-database"
        ).build()
    }

    single { get<ImageDatabase>().imageDao() }

    single<ImageRepository> {
        ImageRepositoryImpl(get(), get())
    }

    viewModel {
        ImageViewModel(get())
    }
}