package pl.sokolowskibartlomiej.catsrecruitment.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.sokolowskibartlomiej.catsrecruitment.data.repository.PhotosRepositoryImpl
import pl.sokolowskibartlomiej.catsrecruitment.domain.repository.PhotosRepository
import pl.sokolowskibartlomiej.catsrecruitment.domain.usecases.LoadPhotosUseCase
import pl.sokolowskibartlomiej.catsrecruitment.presentation.MainViewModel

val mainModule = module {
    single<PhotosRepository> { PhotosRepositoryImpl(get(), get()) }
    single { LoadPhotosUseCase(get()) }

    viewModel { MainViewModel(get()) }
}