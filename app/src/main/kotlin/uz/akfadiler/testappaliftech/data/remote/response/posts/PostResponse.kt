package uz.akfadiler.testappaliftech.data.remote.response.posts

import uz.akfadiler.testappaliftech.data.local.database.posts.PostsEntity
import uz.akfadiler.testappaliftech.domain.model.PostsData

data class PostResponse(
    val userId: Int? = null,
    val id: Int? = null,
    val title: String? = null,
    val body: String? = null
) : java.io.Serializable

fun PostResponse.toPostsData(): PostsData = PostsData(this.id, this.userId, this.title, this.body)

fun PostResponse.toPostsEntity(): PostsEntity =
    PostsEntity(this.id, this.userId, this.title, this.body)