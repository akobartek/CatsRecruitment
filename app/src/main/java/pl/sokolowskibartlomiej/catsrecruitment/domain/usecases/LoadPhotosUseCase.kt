package pl.sokolowskibartlomiej.catsrecruitment.domain.usecases

import pl.sokolowskibartlomiej.catsrecruitment.domain.repository.PhotosRepository

class LoadPhotosUseCase(private val photosRepository: PhotosRepository) {
    suspend operator fun invoke() = photosRepository.loadPhotos()
}