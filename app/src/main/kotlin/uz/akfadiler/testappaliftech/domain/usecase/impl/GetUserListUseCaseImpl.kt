package uz.akfadiler.testappaliftech.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import uz.akfadiler.testappaliftech.data.local.database.users.toUserData
import uz.akfadiler.testappaliftech.data.remote.response.user.toUserData
import uz.akfadiler.testappaliftech.data.remote.response.user.toUserEntity
import uz.akfadiler.testappaliftech.data.repository.app.AppRepository
import uz.akfadiler.testappaliftech.domain.model.MessageData
import uz.akfadiler.testappaliftech.domain.model.ResultData
import uz.akfadiler.testappaliftech.domain.model.UserData
import uz.akfadiler.testappaliftech.domain.usecase.GetUserListUseCase
import uz.akfadiler.testappaliftech.utils.isConnected
import javax.inject.Inject

class GetUserListUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : GetUserListUseCase {
    override fun invoke() = flow<ResultData<List<UserData>>> {
        if (isConnected()) {
            val response = repository.getUserListFromService()
            Timber.d(response.code().toString())
            if (response.isSuccessful) {
                response.body()?.let { list ->
                    repository.insertUsersListFromLocal(list.map { it.toUserEntity() })
                    emit(ResultData.Success(list.map { it.toUserData() }))
                }
            } else {
                when (response.code()) {
                    400 -> {}
                    404 -> {}
                }
            }
        } else {
            emit(ResultData.Success(repository.getUserListFromLocal().map {
                it.toUserData()
            }))
        }
    }.catch {
        Timber.d(it.toString())
        emit(ResultData.Fail(MessageData.Text(it.localizedMessage!!)))
    }.flowOn(Dispatchers.IO)
}