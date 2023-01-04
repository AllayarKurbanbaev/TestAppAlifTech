package uz.akfadiler.testappaliftech.data.local.database.photos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhotosDao {
    @Query("SELECT * FROM PhotosEntity")
    suspend fun getAllPhotos(): List<PhotosEntity>

    @Query("SELECT * FROM PhotosEntity WHERE userId = :userId")
    suspend fun getPhotosByUserId(userId: Int): List<PhotosEntity>

    @Query("DELETE FROM PhotosEntity WHERE userId = :userId")
    suspend fun deletePhotosByUserId(userId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(list: List<PhotosEntity>)

    @Query("DELETE FROM PhotosEntity")
    suspend fun deleteAllPhotos()
}