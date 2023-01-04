package uz.akfadiler.testappaliftech.data.remote.response.photos

import uz.akfadiler.testappaliftech.data.local.database.photos.PhotosEntity
import uz.akfadiler.testappaliftech.domain.model.PhotosData

data class PhotosResponse(
    val albumId: Int? = null,
    val id: Int? = null,
    val title: String? = null,
    val url: String? = null,
    val thumbnailUrl: String? = null
) : java.io.Serializable

fun PhotosResponse.toPhotoData(userId: Int): PhotosData = PhotosData(
    this.id, this.albumId, userId, this.title, this.url, this.thumbnailUrl
)

fun PhotosResponse.toPhotoEntity(userId: Int): PhotosEntity = PhotosEntity(
    this.id, this.albumId, userId, this.title, this.url, this.thumbnailUrl
)