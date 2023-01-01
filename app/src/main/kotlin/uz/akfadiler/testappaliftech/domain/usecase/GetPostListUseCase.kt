package uz.akfadiler.testappaliftech.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.akfadiler.testappaliftech.data.remote.response.posts.PostResponse
import uz.akfadiler.testappaliftech.domain.model.ResultData

interface GetPostListUseCase {
    operator fun invoke() : Flow<ResultData<List<PostResponse>>>
}