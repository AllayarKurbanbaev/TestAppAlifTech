package uz.akfadiler.testappaliftech.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.akfadiler.testappaliftech.data.remote.response.user.UserResponse
import uz.akfadiler.testappaliftech.domain.model.ResultData

interface GetUserByIdUseCase {
    operator fun invoke(id: Int): Flow<ResultData<UserResponse>>
}