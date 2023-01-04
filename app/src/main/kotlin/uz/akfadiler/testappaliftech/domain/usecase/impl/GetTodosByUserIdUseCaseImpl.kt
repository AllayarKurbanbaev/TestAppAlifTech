package uz.akfadiler.testappaliftech.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import uz.akfadiler.testappaliftech.data.local.database.todos.toTodosData
import uz.akfadiler.testappaliftech.data.remote.response.todos.toTodosData
import uz.akfadiler.testappaliftech.data.remote.response.todos.toTodosEntity
import uz.akfadiler.testappaliftech.data.repository.app.AppRepository
import uz.akfadiler.testappaliftech.domain.model.MessageData
import uz.akfadiler.testappaliftech.domain.model.ResultData
import uz.akfadiler.testappaliftech.domain.model.TodosData
import uz.akfadiler.testappaliftech.domain.usecase.GetTodosByUserIdUseCase
import uz.akfadiler.testappaliftech.utils.isConnected
import javax.inject.Inject

class GetTodosByUserIdUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : GetTodosByUserIdUseCase {
    override fun invoke(userId: Int) = flow<ResultData<List<TodosData>>> {
        if (isConnected()) {
            val response = repository.getTodosByUserIdFromService(userId)
            Timber.d(response.code().toString())
            if (response.isSuccessful) {
                response.body()?.let { list ->
                    repository.insertTodosListFromLocal(list.map {
                        it.toTodosEntity()
                    }, userId)
                    emit(ResultData.Success(list.map { it.toTodosData() }))
                }
            } else {
                when (response.code()) {
                    400 -> {}
                    404 -> {}
                }
            }
        } else {
            emit(ResultData.Success(repository.getTodosByUserIdFromLocal(userId).map {
                it.toTodosData()
            }))
        }
    }.catch {
        emit(ResultData.Fail(MessageData.Text(it.localizedMessage!!)))
    }.flowOn(Dispatchers.IO)
}