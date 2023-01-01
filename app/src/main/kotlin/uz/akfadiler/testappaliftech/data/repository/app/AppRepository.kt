package uz.akfadiler.testappaliftech.data.repository.app

import retrofit2.Response
import uz.akfadiler.testappaliftech.data.remote.response.posts.PostResponse
import uz.akfadiler.testappaliftech.data.remote.response.user.UserResponse

interface AppRepository {
    suspend fun getUserListFromService() : Response<List<UserResponse>>
    suspend fun getUserByIdFromService(id : Int) : Response<UserResponse>
    suspend fun getPostsListFromService() : Response<List<PostResponse>>
    suspend fun getPostById(id : Int) : Response<List<PostResponse>>
    suspend fun getPostByUserId(userId : Int) : Response<List<PostResponse>>
    suspend fun deletePostById(id : Int) : Response<Unit>
}