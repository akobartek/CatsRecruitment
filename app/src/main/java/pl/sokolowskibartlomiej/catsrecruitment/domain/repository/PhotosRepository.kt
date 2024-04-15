package pl.sokolowskibartlomiej.catsrecruitment.domain.repository

import pl.sokolowskibartlomiej.catsrecruitment.domain.model.PhotoItem

interface PhotosRepository {
    suspend fun loadPhotos(): List<PhotoItem>
}