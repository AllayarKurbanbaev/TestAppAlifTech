package uz.akfadiler.testappaliftech.presentation.viewmodel.detail

import androidx.lifecycle.LiveData
import uz.akfadiler.testappaliftech.data.remote.response.albums.AlbumsResponse
import uz.akfadiler.testappaliftech.data.remote.response.photos.PhotosResponse
import uz.akfadiler.testappaliftech.data.remote.response.posts.PostResponse
import uz.akfadiler.testappaliftech.data.remote.response.todos.TodosResponse

interface DetailViewModel {
    val errorLiveData: LiveData<String>
    val progressLiveData: LiveData<Boolean>
    val loadPostsLiveData: LiveData<List<PostResponse>>
    val deletePostLiveData: LiveData<Unit>
    val backPressedLiveData: LiveData<Unit>
    val loadAlbumsLiveData: LiveData<List<AlbumsResponse>>
    val loadTodosLiveData: LiveData<List<TodosResponse>>
    val loadPhotosLiveData: LiveData<List<PhotosResponse>>

    fun loadPosts(userId: Int)
    fun loadAlbums(userId: Int)
    fun loadTodos(userId: Int)
    fun loadPhotos(userId: Int)
    fun deletePost(postId: Int)
    fun onBackPressed()
}