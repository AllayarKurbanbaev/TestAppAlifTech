package uz.akfadiler.testappaliftech.presentation.viewmodel.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.akfadiler.testappaliftech.data.remote.response.albums.AlbumsResponse
import uz.akfadiler.testappaliftech.data.remote.response.photos.PhotosResponse
import uz.akfadiler.testappaliftech.data.remote.response.posts.PostResponse
import uz.akfadiler.testappaliftech.data.remote.response.todos.TodosResponse
import uz.akfadiler.testappaliftech.domain.model.asSuccess
import uz.akfadiler.testappaliftech.domain.model.onSuccess
import uz.akfadiler.testappaliftech.domain.model.onText
import uz.akfadiler.testappaliftech.domain.usecase.*
import javax.inject.Inject

@HiltViewModel
class DetailViewModelImpl @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val postByUserIdUseCase: GetPostByUserIdUseCase,
    private val deletePostByIdUseCase: DeletePostByIdUseCase,
    private val albumsByUserIdUseCase: GetAlbumsByUserIdUseCase,
    private val todosByUserIdUseCase: GetTodosByUserIdUseCase,
    private val photoByUserIdUseCase: GetPhotosByUserIdUseCase
) : ViewModel(), DetailViewModel {
    override val errorLiveData: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    override val progressLiveData: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    override val loadPostsLiveData: MutableLiveData<List<PostResponse>> by lazy { MutableLiveData<List<PostResponse>>() }
    override val deletePostLiveData: MutableLiveData<Unit> by lazy { MutableLiveData<Unit>() }
    override val backPressedLiveData: MutableLiveData<Unit> by lazy { MutableLiveData<Unit>() }
    override val loadAlbumsLiveData: MutableLiveData<List<AlbumsResponse>> by lazy { MutableLiveData<List<AlbumsResponse>>() }
    override val loadTodosLiveData: MutableLiveData<List<TodosResponse>> by lazy { MutableLiveData<List<TodosResponse>>() }
    override val loadPhotosLiveData: MutableLiveData<List<PhotosResponse>> by lazy { MutableLiveData<List<PhotosResponse>>() }

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

    override fun loadAlbums(userId: Int) {
        progressLiveData.value = true
        albumsByUserIdUseCase.invoke(userId).onEach {
            progressLiveData.value = false
            it.onSuccess {
                val data = it.asSuccess.data
                loadAlbumsLiveData.value = data
            }
            it.onText {
                errorLiveData.value = message
            }
        }.launchIn(viewModelScope)
    }

    override fun loadTodos(userId: Int) {
        progressLiveData.value = true
        todosByUserIdUseCase.invoke(userId).onEach {
            progressLiveData.value = false
            it.onSuccess {
                val data = it.asSuccess.data
                loadTodosLiveData.value = data
            }
            it.onText {
                errorLiveData.value = message
            }
        }.launchIn(viewModelScope)
    }

    override fun loadPhotos(userId: Int) {
        progressLiveData.value = true
        photoByUserIdUseCase.invoke(userId).onEach {
            progressLiveData.value = false
            it.onSuccess {
                val data = it.asSuccess.data
                loadPhotosLiveData.value = data
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