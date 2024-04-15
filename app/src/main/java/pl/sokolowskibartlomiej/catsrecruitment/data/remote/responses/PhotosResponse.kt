package pl.sokolowskibartlomiej.catsrecruitment.data.remote.responses

import java.util.Date

data class PhotosResponse(
    val title: String?,
    val link: String?,
    val description: String?,
    val modifier: Date?,
    val generator: String?,
    val items: List<ItemResponse>
)