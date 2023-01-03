package uz.akfadiler.testappaliftech.data.remote

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import uz.akfadiler.testappaliftech.data.remote.response.albums.AlbumsResponse
import uz.akfadiler.testappaliftech.data.remote.response.photos.PhotosResponse
import uz.akfadiler.testappaliftech.data.remote.response.posts.PostResponse
import uz.akfadiler.testappaliftech.data.remote.response.todos.TodosResponse
import uz.akfadiler.testappaliftech.data.remote.response.user.UserResponse

interface ApiService {

    @GET("/users")
    suspend fun getUserList(): Response<List<UserResponse>>

    @GET
    suspend fun getUserById(@Url url: String): Response<UserResponse>

    @GET("/posts")
    suspend fun getPostsList(): Response<List<PostResponse>>

    @GET
    suspend fun getPostById(@Url url: String): Response<PostResponse>

    @GET("/posts")
    suspend fun getPostByUserId(@Query("userId") userId: Int): Response<List<PostResponse>>

    @DELETE
    suspend fun deletePostById(@Url url: String): Response<Unit>

    @GET("/albums")
    suspend fun getAlbumsByUserId(@Query("userId") userId: Int): Response<List<AlbumsResponse>>

    @GET("/todos")
    suspend fun getTodosByUserId(@Query("userId") userId: Int): Response<List<TodosResponse>>

    @GET("/photos")
    suspend fun getPhotosByUserId(@Query("userId") userId: Int): Response<List<PhotosResponse>>
}