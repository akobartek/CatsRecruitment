package pl.sokolowskibartlomiej.catsrecruitment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.sokolowskibartlomiej.catsrecruitment.data.local.entities.PhotoItemEntity

@Dao
interface PhotoItemsDao {
    @Query("SELECT * FROM photo_item")
    suspend fun getAllItems(): List<PhotoItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<PhotoItemEntity>)

    @Query("DELETE FROM photo_item")
    suspend fun deleteAllItems(): Int
}