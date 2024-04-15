package pl.sokolowskibartlomiej.catsrecruitment.di

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pl.sokolowskibartlomiej.catsrecruitment.data.remote.PhotosApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.flickr.com/services/"

private fun provideHttpClient(appContext: Context): OkHttpClient =
    OkHttpClient.Builder()
        .cache(Cache(File(appContext.cacheDir, "http-cache"), 7 * 1024 * 1024))
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .callTimeout(10, TimeUnit.SECONDS)
        .build()

private fun provideRetrofit(httpClient: OkHttpClient) =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

private inline fun <reified T> createUrlService(retrofit: Retrofit) =
    retrofit.create(T::class.java)

val networkModule = module {
    single { provideHttpClient(androidContext()) }
    single { provideRetrofit(get()) }
    single { createUrlService<PhotosApi>(get()) }
}