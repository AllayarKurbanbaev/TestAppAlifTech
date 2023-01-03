package uz.akfadiler.testappaliftech.data.repository.app

import retrofit2.Response
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
    suspend fun getPhotosByUserIdFromService(userId: Int) : Response<List<PhotosResponse>>
}