package uz.akfadiler.testappaliftech.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.akfadiler.testappaliftech.data.remote.response.posts.PostResponse
import uz.akfadiler.testappaliftech.domain.model.ResultData

interface GetPostByUserIdUseCase {
    operator fun invoke(userId: Int): Flow<ResultData<List<PostResponse>>>
}