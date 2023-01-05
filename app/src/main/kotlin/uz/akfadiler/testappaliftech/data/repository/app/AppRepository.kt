package uz.akfadiler.testappaliftech.data.repository.app

import retrofit2.Response
import uz.akfadiler.testappaliftech.data.local.database.photos.PhotosEntity
import uz.akfadiler.testappaliftech.data.local.database.posts.PostsEntity
import uz.akfadiler.testappaliftech.data.local.database.todos.TodosEntity
import uz.akfadiler.testappaliftech.data.local.database.users.UserEntity
import uz.akfadiler.testappaliftech.data.remote.response.albums.AlbumsResponse
import uz.akfadiler.testappaliftech.data.remote.response.photos.PhotosResponse
import uz.akfadiler.testappaliftech.data.remote.response.posts.PostResponse
import uz.akfadiler.testappaliftech.data.remote.response.todos.TodosResponse
import uz.akfadiler.testappaliftech.data.remote.response.user.UserResponse

interface AppRepository {
    suspend fun getUserListFromService(): Response<List<UserResponse>>
    suspend fun getUserByIdFromService(id: Int): Response<UserResponse>
    suspend fun getPostsListFromService(): Response<List<PostResponse>>
    suspend fun getPostByIdFromService(id: Int): Response<PostResponse>
    suspend fun getPostByUserIdFromService(userId: Int): Response<List<PostResponse>>
    suspend fun deletePostByIdFromService(id: Int): Response<Unit>
    suspend fun getAlbumsByUserIdFromService(userId: Int): Response<List<AlbumsResponse>>
    suspend fun getTodosByUserIdFromService(userId: Int): Response<List<TodosResponse>>
    suspend fun getPhotosByUserIdFromService(userId: Int): Response<List<PhotosResponse>>

    suspend fun getUserListFromLocal(): List<UserEntity>
    suspend fun insertUsersListFromLocal(list: List<UserEntity>)
    suspend fun getPostsByUserIdFromLocal(userId: Int): List<PostsEntity>
    suspend fun insertPostsListFromLocal(list: List<PostsEntity>, userId: Int)
    suspend fun getPhotosByUserIdFromLocal(userId: Int): List<PhotosEntity>
    suspend fun insertPhotosListFromLocal(list: List<PhotosEntity>, userId: Int)
    suspend fun getTodosByUserIdFromLocal(userId: Int): List<TodosEntity>
    suspend fun insertTodosListFromLocal(list: List<TodosEntity>, userId: Int)
    suspend fun deletePostsByIdFromLocal(id : Int)
}