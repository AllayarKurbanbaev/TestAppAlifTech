package uz.akfadiler.testappaliftech.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import uz.akfadiler.testappaliftech.data.remote.response.albums.AlbumsResponse
import uz.akfadiler.testappaliftech.data.repository.app.AppRepository
import uz.akfadiler.testappaliftech.domain.model.MessageData
import uz.akfadiler.testappaliftech.domain.model.ResultData
import uz.akfadiler.testappaliftech.domain.usecase.GetAlbumsByUserIdUseCase
import uz.akfadiler.testappaliftech.utils.isConnected
import javax.inject.Inject

class GetAlbumsByUserIdUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : GetAlbumsByUserIdUseCase {
    override fun invoke(userId: Int) = flow<ResultData<List<AlbumsResponse>>> {
        if (isConnected()) {
            val response = repository.getAlbumsByUserIdFromService(userId)
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
            emit(ResultData.Fail(MessageData.Text("Internet not connected!")))
        }
    }.catch {
        emit(ResultData.Fail(MessageData.Text(it.localizedMessage!!)))
    }.flowOn(Dispatchers.IO)
}