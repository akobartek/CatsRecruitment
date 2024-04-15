package pl.sokolowskibartlomiej.catsrecruitment.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.sokolowskibartlomiej.catsrecruitment.domain.model.PhotoItem
import java.util.Date

@Entity(tableName = "photo_item")
data class PhotoItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "link") val link: String?,
    @ColumnInfo(name = "photo_url") val photoUrl: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "published") val published: Long?,
    @ColumnInfo(name = "author") val author: String?
) {
    fun toDomainObject() = PhotoItem(
        title = title,
        link = link,
        photoUrl = photoUrl,
        description = description,
        published = published?.let { Date(it) },
        author = author
    )

    companion object {
        fun PhotoItem.fromDomainObject() = PhotoItemEntity(
            title = title,
            link = link,
            photoUrl = photoUrl,
            description = description,
            published = published?.time,
            author = author
        )
    }
}