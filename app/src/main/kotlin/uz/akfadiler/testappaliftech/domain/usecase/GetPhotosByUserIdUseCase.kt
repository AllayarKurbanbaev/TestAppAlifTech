package uz.akfadiler.testappaliftech.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.akfadiler.testappaliftech.data.remote.response.photos.PhotosResponse
import uz.akfadiler.testappaliftech.domain.model.PhotosData
import uz.akfadiler.testappaliftech.domain.model.ResultData

interface GetPhotosByUserIdUseCase {
    operator fun invoke(userId : Int) : Flow<ResultData<List<PhotosData>>>
}