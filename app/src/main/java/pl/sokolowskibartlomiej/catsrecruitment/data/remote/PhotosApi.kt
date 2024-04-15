package pl.sokolowskibartlomiej.catsrecruitment.data.remote

import pl.sokolowskibartlomiej.catsrecruitment.data.remote.responses.PhotosResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotosApi {
    @GET("feeds/photos_public.gne?format=json&tags=cat&nojsoncallback=1")
    suspend fun getPhotos(): PhotosResponse
}