package uz.akfadiler.testappaliftech.presentation.viewmodel.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.akfadiler.testappaliftech.data.remote.response.posts.PostResponse
import uz.akfadiler.testappaliftech.domain.model.asSuccess
import uz.akfadiler.testappaliftech.domain.model.onSuccess
import uz.akfadiler.testappaliftech.domain.model.onText
import uz.akfadiler.testappaliftech.domain.usecase.DeletePostByIdUseCase
import uz.akfadiler.testappaliftech.domain.usecase.GetPostByUserIdUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModelImpl @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val postByUserIdUseCase: GetPostByUserIdUseCase,
    private val deletePostByIdUseCase: DeletePostByIdUseCase
) : ViewModel(), DetailViewModel {
    override val errorLiveData: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    override val progressLiveData: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    override val loadPostsLiveData: MutableLiveData<List<PostResponse>> by lazy { MutableLiveData<List<PostResponse>>() }
    override val deletePostLiveData: MutableLiveData<Unit> by lazy { MutableLiveData<Unit>() }
    override val backPressedLiveData: MutableLiveData<Unit> by lazy { MutableLiveData<Unit>() }

    override fun loadPosts(userId: Int) {
        progressLiveData.value = true
        postByUserIdUseCase.invoke(userId).onEach {
            progressLiveData.value = false
            it.onSuccess {
                val data = it.asSuccess.data
                loadPostsLiveData.value = data
            }
            it.onText {
                errorLiveData.value = message
            }
        }.launchIn(viewModelScope)
    }

    override fun deletePost(postId: Int) {
        progressLiveData.value = true
        deletePostByIdUseCase.invoke(postId).onEach {
            progressLiveData.value = false
            it.onSuccess {
                val data = it.asSuccess.data
                deletePostLiveData.value = data
            }
            it.onText {
                errorLiveData.value = message
            }
        }.launchIn(viewModelScope)
    }

    override fun onBackPressed() {
        backPressedLiveData.value = Unit
    }
}