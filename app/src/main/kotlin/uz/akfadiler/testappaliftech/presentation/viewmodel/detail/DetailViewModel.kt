package uz.akfadiler.testappaliftech.presentation.viewmodel.detail

import androidx.lifecycle.LiveData
import uz.akfadiler.testappaliftech.data.remote.response.posts.PostResponse

interface DetailViewModel {
    val errorLiveData: LiveData<String>
    val progressLiveData: LiveData<Boolean>
    val loadPostsLiveData: LiveData<List<PostResponse>>
    val deletePostLiveData: LiveData<Unit>
    val backPressedLiveData : LiveData<Unit>

    fun loadPosts(userId: Int)
    fun deletePost(postId: Int)
    fun onBackPressed()
}