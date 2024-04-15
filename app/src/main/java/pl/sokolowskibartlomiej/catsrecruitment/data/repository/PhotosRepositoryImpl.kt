package pl.sokolowskibartlomiej.catsrecruitment.data.repository

import pl.sokolowskibartlomiej.catsrecruitment.data.local.PhotoItemsDao
import pl.sokolowskibartlomiej.catsrecruitment.data.local.entities.PhotoItemEntity.Companion.fromDomainObject
import pl.sokolowskibartlomiej.catsrecruitment.data.remote.PhotosApi
import pl.sokolowskibartlomiej.catsrecruitment.domain.model.PhotoItem
import pl.sokolowskibartlomiej.catsrecruitment.domain.repository.PhotosRepository

class PhotosRepositoryImpl(
    private val api: PhotosApi,
    private val dao: PhotoItemsDao
): PhotosRepository {
    override suspend fun loadPhotos(): List<PhotoItem> {
        return try {
            val items = api.getPhotos().items
                .sortedBy { it.published }
                .map { it.toDomainObject() }
            saveItemsToDatabase(items)
            items
        } catch (exc: Exception) {
            dao.getAllItems().map { it.toDomainObject() }
        }
    }

    private suspend fun saveItemsToDatabase(items: List<PhotoItem>) {
        dao.deleteAllItems()
        dao.insertItems(items.map { it.fromDomainObject() })
    }
}