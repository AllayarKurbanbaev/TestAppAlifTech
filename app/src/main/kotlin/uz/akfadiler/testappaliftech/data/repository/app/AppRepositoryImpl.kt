package uz.akfadiler.testappaliftech.data.repository.app

import retrofit2.Response
import uz.akfadiler.testappaliftech.data.local.database.photos.PhotosDao
import uz.akfadiler.testappaliftech.data.local.database.photos.PhotosEntity
import uz.akfadiler.testappaliftech.data.local.database.posts.PostsDao
import uz.akfadiler.testappaliftech.data.local.database.posts.PostsEntity
import uz.akfadiler.testappaliftech.data.local.database.todos.TodosDao
import uz.akfadiler.testappaliftech.data.local.database.todos.TodosEntity
import uz.akfadiler.testappaliftech.data.local.database.users.UserDao
import uz.akfadiler.testappaliftech.data.local.database.users.UserEntity
import uz.akfadiler.testappaliftech.data.remote.ApiService
import uz.akfadiler.testappaliftech.data.remote.response.albums.AlbumsResponse
import uz.akfadiler.testappaliftech.data.remote.response.photos.PhotosResponse
import uz.akfadiler.testappaliftech.data.remote.response.posts.PostResponse
import uz.akfadiler.testappaliftech.data.remote.response.todos.TodosResponse
import uz.akfadiler.testappaliftech.data.remote.response.user.UserResponse
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val userDao: UserDao,
    private val postsDao: PostsDao,
    private val photosDao: PhotosDao,
    private val todosDao: TodosDao
) : AppRepository {
    override suspend fun getUserListFromService(): Response<List<UserResponse>> {
        return api.getUserList()
    }

    override suspend fun getUserByIdFromService(id: Int): Response<UserResponse> {
        return api.getUserById("users/$id")
    }

    override suspend fun getPostsListFromService(): Response<List<PostResponse>> {
        return api.getPostsList()
    }

    override suspend fun getPostByIdFromService(id: Int): Response<PostResponse> {
        return api.getPostById("posts/$id")
    }

    override suspend fun getPostByUserIdFromService(userId: Int): Response<List<PostResponse>> {
        return api.getPostByUserId(userId)
    }

    override suspend fun deletePostByIdFromService(id: Int): Response<Unit> {
        return api.deletePostById("/posts/$id")
    }

    override suspend fun getAlbumsByUserIdFromService(userId: Int): Response<List<AlbumsResponse>> {
        return api.getAlbumsByUserId(userId)
    }

    override suspend fun getTodosByUserIdFromService(userId: Int): Response<List<TodosResponse>> {
        return api.getTodosByUserId(userId)
    }

    override suspend fun getPhotosByUserIdFromService(userId: Int): Response<List<PhotosResponse>> {
        return api.getPhotosByUserId(userId)
    }

    override suspend fun getUserListFromLocal(): List<UserEntity> {
        return userDao.getAllUsers()
    }

    override suspend fun insertUsersListFromLocal(list: List<UserEntity>) {
        userDao.deleteAllUsers()
        userDao.insertUsers(list)
    }

    override suspend fun getPostsByUserIdFromLocal(userId: Int): List<PostsEntity> {
        return postsDao.getPostsByUserId(userId)
    }

    override suspend fun insertPostsListFromLocal(list: List<PostsEntity>, userId: Int) {
        postsDao.deletePostsByUserId(userId)
        postsDao.insertPosts(list)
    }

    override suspend fun getPhotosByUserIdFromLocal(userId: Int): List<PhotosEntity> {
        return photosDao.getPhotosByUserId(userId)
    }

    override suspend fun insertPhotosListFromLocal(list: List<PhotosEntity>, userId: Int) {
        photosDao.deletePhotosByUserId(userId)
        photosDao.insertPhotos(list)
    }

    override suspend fun getTodosByUserIdFromLocal(userId: Int): List<TodosEntity> {
        return todosDao.getTodosByUserId(userId)
    }

    override suspend fun insertTodosListFromLocal(list: List<TodosEntity>, userId: Int) {
        todosDao.deleteTodosByUserId(userId)
        todosDao.insertTodos(list)
    }
}