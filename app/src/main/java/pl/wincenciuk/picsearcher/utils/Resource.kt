package pl.wincenciuk.picsearcher.utils

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Error<T>(message: String) : Resource<T>(message = message)
    class Success<T>(data: T?) : Resource<T>(data = data)
//    class Loading<T> : Resource<T>()
}