package pl.sokolowskibartlomiej.catsrecruitment.domain.model

import java.util.Date

data class PhotoItem(
    val title: String?,
    val link: String?,
    val photoUrl: String?,
    val description: String?,
    val published: Date?,
    val author: String?
)
