package uz.akfadiler.testappaliftech.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.akfadiler.testappaliftech.data.remote.response.albums.AlbumsResponse
import uz.akfadiler.testappaliftech.domain.model.ResultData

interface GetAlbumsByUserIdUseCase {
    operator fun invoke(userId: Int): Flow<ResultData<List<AlbumsResponse>>>
}