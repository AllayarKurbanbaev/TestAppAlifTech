package uz.akfadiler.testappaliftech.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import uz.akfadiler.testappaliftech.data.local.database.posts.toPostsData
import uz.akfadiler.testappaliftech.data.remote.response.posts.toPostsData
import uz.akfadiler.testappaliftech.data.remote.response.posts.toPostsEntity
import uz.akfadiler.testappaliftech.data.repository.app.AppRepository
import uz.akfadiler.testappaliftech.domain.model.MessageData
import uz.akfadiler.testappaliftech.domain.model.PostsData
import uz.akfadiler.testappaliftech.domain.model.ResultData
import uz.akfadiler.testappaliftech.domain.usecase.GetPostByUserIdUseCase
import uz.akfadiler.testappaliftech.utils.isConnected
import javax.inject.Inject

class GetPostByUserIdUseCaseImpl @Inject constructor(private val repository: AppRepository) :
    GetPostByUserIdUseCase {
    override fun invoke(userId: Int) = flow<ResultData<List<PostsData>>> {
        if (isConnected()) {
            val response = repository.getPostByUserIdFromService(userId)
            Timber.d(response.code().toString())
            if (response.isSuccessful) {
                response.body()?.let { list ->
                    repository.insertPostsListFromLocal(list.map { it.toPostsEntity() }, userId)
                    emit(ResultData.Success(list.map { it.toPostsData() }))
                }
            } else {
                when (response.code()) {
                    400 -> {}
                    404 -> {}
                }
            }
        } else {
            emit(ResultData.Success(repository.getPostsByUserIdFromLocal(userId).map {
                it.toPostsData()
            }))
        }
    }.catch {
        emit(ResultData.Fail(MessageData.Text(it.localizedMessage!!)))
    }.flowOn(Dispatchers.IO)

}