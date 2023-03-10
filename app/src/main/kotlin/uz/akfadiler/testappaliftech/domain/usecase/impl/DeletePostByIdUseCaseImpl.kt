package uz.akfadiler.testappaliftech.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import uz.akfadiler.testappaliftech.data.repository.app.AppRepository
import uz.akfadiler.testappaliftech.domain.model.MessageData
import uz.akfadiler.testappaliftech.domain.model.ResultData
import uz.akfadiler.testappaliftech.domain.usecase.DeletePostByIdUseCase
import uz.akfadiler.testappaliftech.utils.isConnected
import javax.inject.Inject

class DeletePostByIdUseCaseImpl @Inject constructor(private val repository: AppRepository) :
    DeletePostByIdUseCase {
    override fun invoke(id: Int) = flow<ResultData<Unit>> {
        if (isConnected()) {
            val response = repository.deletePostByIdFromService(id)
            Timber.d(response.code().toString())
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(ResultData.Success(it))
                }
            } else {
                when (response.code()) {
                    400 -> {}
                    404 -> {}
                }
            }
        } else {
            emit(ResultData.Success(repository.deletePostsByIdFromLocal(id)))
        }
    }.catch {
        emit(ResultData.Fail(MessageData.Text(it.localizedMessage!!)))
    }.flowOn(Dispatchers.IO)

}