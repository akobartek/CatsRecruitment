package pl.sokolowskibartlomiej.catsrecruitment.di

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import pl.sokolowskibartlomiej.catsrecruitment.data.local.PhotoItemsDao
import pl.sokolowskibartlomiej.catsrecruitment.data.local.PhotoItemsDatabase

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            PhotoItemsDatabase::class.java,
            "photo_items.db"
        ).build()
    }
    single<PhotoItemsDao> {
        val db = get<PhotoItemsDatabase>()
        db.photoItemsDao()
    }
}