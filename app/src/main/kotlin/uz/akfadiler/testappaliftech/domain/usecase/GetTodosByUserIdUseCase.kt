package uz.akfadiler.testappaliftech.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.akfadiler.testappaliftech.data.remote.response.todos.TodosResponse
import uz.akfadiler.testappaliftech.domain.model.ResultData

interface GetTodosByUserIdUseCase {
    operator fun invoke(userId: Int): Flow<ResultData<List<TodosResponse>>>
}