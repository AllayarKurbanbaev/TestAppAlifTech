package uz.akfadiler.testappaliftech.data.local.database.photos

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.akfadiler.testappaliftech.domain.model.PhotosData

@Entity
data class PhotosEntity(
    @PrimaryKey val id: Int?,
    val albumId: Int?,
    val userId: Int?,
    val title: String?,
    val url: String?,
    val thumbnailUrl: String?
)

fun PhotosEntity.toPhotoData(): PhotosData =
    PhotosData(this.id, this.userId, this.albumId, this.title, this.url, this.thumbnailUrl)
