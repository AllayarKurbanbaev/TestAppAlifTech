package uz.akfadiler.testappaliftech.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.akfadiler.testappaliftech.domain.model.ResultData

interface DeletePostByIdUseCase {
    operator fun invoke(id: Int): Flow<ResultData<Unit>>
}