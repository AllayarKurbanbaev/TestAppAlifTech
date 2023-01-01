package uz.akfadiler.testappaliftech.data.repository.app

import retrofit2.Response
import uz.akfadiler.testappaliftech.data.remote.ApiService
import uz.akfadiler.testappaliftech.data.remote.response.posts.PostResponse
import uz.akfadiler.testappaliftech.data.remote.response.user.UserResponse
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val api: ApiService
) : AppRepository {
    override suspend fun getUserListFromService(): Response<List<UserResponse>> {
        return api.getUserList()
    }

    override suspend fun getUserByIdFromService(id: Int): Response<UserResponse> {
        return api.getUserById("users/$id")
    }

    override suspend fun getPostsListFromService(): Response<List<PostResponse>> {
        return api.getPostsList()
    }

    override suspend fun getPostByIdFromService(id: Int): Response<PostResponse> {
        return api.getPostById("posts/$id")
    }

    override suspend fun getPostByUserIdFromService(userId: Int): Response<List<PostResponse>> {
        return api.getPostByUserId(userId)
    }

    override suspend fun deletePostByIdFromService(id: Int): Response<Unit> {
        return api.deletePostById("/posts/$id")
    }
}