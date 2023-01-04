package uz.akfadiler.testappaliftech.data.local.database.posts

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.akfadiler.testappaliftech.domain.model.PostsData

@Entity
data class PostsEntity(
    @PrimaryKey val id: Int?, val userId: Int?, val title: String?, val body: String?
)

fun PostsEntity.toPostsData(): PostsData = PostsData(
    this.id, this.userId, this.title, this.body
)