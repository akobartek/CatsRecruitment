package pl.sokolowskibartlomiej.catsrecruitment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.sokolowskibartlomiej.catsrecruitment.data.local.entities.PhotoItemEntity

@Database(
    entities = [PhotoItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PhotoItemsDatabase : RoomDatabase() {
    abstract fun photoItemsDao(): PhotoItemsDao
}