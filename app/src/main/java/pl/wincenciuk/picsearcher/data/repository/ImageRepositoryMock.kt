package pl.wincenciuk.picsearcher.data.repository

import pl.wincenciuk.picsearcher.data.model.Hit
import pl.wincenciuk.picsearcher.data.model.PixabayResponse
import pl.wincenciuk.picsearcher.utils.Resource

class ImageRepositoryMock : ImageRepository {
    override suspend fun getImages(query: String): Resource<PixabayResponse> {
        return Resource.Success(
            PixabayResponse(
                total = 100,
                totalHits = 1000,
                hits = listOf(
                    Hit(
                        id = 1,
                        pageURL = "https://cdn.pixabay.com/photo/2022/12/26/13/50/flower-7679117_150.jpg",
                        collections = 20,
                        comments = 50,
                        downloads = 1000,
                        imageHeight = 2250,
                        imageSize = 477777,
                        imageWidth = 4000,
                        largeImageURL = "https://pixabay.com/get/g5970389f4f7ec9bcdb03c6d12fb9fbda09b7cd03108eccb4b176161ecd93abe9ccb3684c655adcc2e8dca45f24f91ecdecd0e751001d4e444dfef0a83bf72358_1280.jpg",
                        likes = 999,
                        previewHeight = 84,
                        previewURL = "https://cdn.pixabay.com/photo/2022/12/26/13/50/flower-7679117_150.jpg",
                        previewWidth = 150,
                        tags = "flower, stamens, hypericum, beautiful flowers, macro, flower background",
                        type = "photo",
                        user = "ziomo",
                        userImageURL = "",
                        user_id = 34223,
                        views = 2800,
                        webformatHeight = 427,
                        webformatURL = "https://pixabay.com/get/g13b6a14841a5a69749be73aae3a6ddabef16c0c6c95c638cda57b49a9d3a57e9fee16177830da2c4629b6d05182db31853a3e21e4b2d51717440b70f34c903f5_640.jp",
                        webformatWidth = 640,
                    ),
                    Hit(
                        id = 2,
                        pageURL = "https://pixabay.com/photos/flower-petals-bloom-yellow-yellow-4750726/",
                        collections = 30,
                        comments = 60,
                        downloads = 2000,
                        imageHeight = 3250,
                        imageSize = 577777,
                        imageWidth = 4500,
                        largeImageURL = "https://pixabay.com/get/g9db838c8a96266b6000e0a050db5ab50e6ac8eab7ff5998b27f6da446c7b49057ee4c4ff8a7238334dc215f5bfb774175405c11f3d939f42358c5b0a7a8d1e55_1280.jpg",
                        likes = 2999,
                        previewHeight = 89,
                        previewURL = "https://cdn.pixabay.com/photo/2020/01/08/17/32/flower-4750726_150.jpg",
                        previewWidth = 170,
                        tags = "flower, petals, bloom, beautiful flowers, yellow",
                        type = "photo",
                        user = "ziomo1",
                        userImageURL = "",
                        user_id = 14223,
                        views = 4800,
                        webformatHeight = 457,
                        webformatURL = "https://pixabay.com/get/gd7ba85e491e1ffb09c8300428d5fa20c4e2dac034eb6c783318837f1ce20180bcfb4c7b69be24045057087347e2dd8ad7563444ed87cc3fdccb90bfb84357821_640.jp",
                        webformatWidth = 610,
                    )
                )
            )
        )
    }
}