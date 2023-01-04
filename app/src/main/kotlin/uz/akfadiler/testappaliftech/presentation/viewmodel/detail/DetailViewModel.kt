package uz.akfadiler.testappaliftech.presentation.viewmodel.detail

import androidx.lifecycle.LiveData
import uz.akfadiler.testappaliftech.data.remote.response.albums.AlbumsResponse
import uz.akfadiler.testappaliftech.data.remote.response.photos.PhotosResponse
import uz.akfadiler.testappaliftech.data.remote.response.posts.PostResponse
import uz.akfadiler.testappaliftech.data.remote.response.todos.TodosResponse
import uz.akfadiler.testappaliftech.domain.model.PhotosData
import uz.akfadiler.testappaliftech.domain.model.PostsData
import uz.akfadiler.testappaliftech.domain.model.TodosData

interface DetailViewModel {
    val errorLiveData: LiveData<String>
    val progressLiveData: LiveData<Boolean>
    val loadPostsLiveData: LiveData<List<PostsData>>
    val deletePostLiveData: LiveData<Unit>
    val backPressedLiveData: LiveData<Unit>
    val loadAlbumsLiveData: LiveData<List<AlbumsResponse>>
    val loadTodosLiveData: LiveData<List<TodosData>>
    val loadPhotosLiveData: LiveData<List<PhotosData>>

    fun loadPosts(userId: Int)
    fun loadAlbums(userId: Int)
    fun loadTodos(userId: Int)
    fun loadPhotos(userId: Int)
    fun deletePost(postId: Int)
    fun onBackPressed()
}