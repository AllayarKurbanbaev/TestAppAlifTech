package uz.akfadiler.testappaliftech.data.local.database.posts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostsDao {
    @Query("SELECT * FROM PostsEntity")
    suspend fun getAllPosts(): List<PostsEntity>

    @Query("SELECT * FROM PostsEntity WHERE userId = :userId")
    suspend fun getPostsByUserId(userId: Int) : List<PostsEntity>

    @Query("DELETE FROM PostsEntity WHERE userId = :userId")
    suspend fun deletePostsByUserId(userId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(list: List<PostsEntity>)

    @Query("DELETE FROM PostsEntity")
    suspend fun deleteAllPosts()
}