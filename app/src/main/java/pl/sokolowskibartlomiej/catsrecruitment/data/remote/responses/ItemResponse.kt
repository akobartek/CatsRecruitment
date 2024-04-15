package pl.sokolowskibartlomiej.catsrecruitment.data.remote.responses

import com.google.gson.annotations.SerializedName
import pl.sokolowskibartlomiej.catsrecruitment.domain.model.PhotoItem
import java.util.Date

data class ItemResponse(
    val title: String?,
    val link: String?,
    val media: MediaResponse?,
    @SerializedName("date_taken") val dateTaken: Date?,
    val description: String?,
    val published: Date?,
    val author: String?,
    @SerializedName("author_id") val authorId: String?,
    val tags: String?
) {
    fun toDomainObject() = PhotoItem(
        title = title,
        link = link,
        photoUrl = media?.m,
        description = description,
        published = published,
        author = author
    )
}
