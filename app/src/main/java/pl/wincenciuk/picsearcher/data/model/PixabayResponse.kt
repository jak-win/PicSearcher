package pl.wincenciuk.picsearcher.data.model

data class PixabayResponse(
    val hits: List<Hit> = listOf(),
    val total: Int? = null,
    val totalHits: Int? = null
)