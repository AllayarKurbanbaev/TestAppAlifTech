package uz.akfadiler.testappaliftech.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.akfadiler.testappaliftech.data.local.database.photos.PhotosDao
import uz.akfadiler.testappaliftech.data.local.database.photos.PhotosEntity
import uz.akfadiler.testappaliftech.data.local.database.posts.PostsDao
import uz.akfadiler.testappaliftech.data.local.database.posts.PostsEntity
import uz.akfadiler.testappaliftech.data.local.database.todos.TodosDao
import uz.akfadiler.testappaliftech.data.local.database.todos.TodosEntity
import uz.akfadiler.testappaliftech.data.local.database.users.UserDao
import uz.akfadiler.testappaliftech.data.local.database.users.UserEntity

@Database(
    entities = [UserEntity::class, PostsEntity::class, PhotosEntity::class, TodosEntity::class],
    version = 3,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getPostsDao(): PostsDao
    abstract fun getPhotosDao(): PhotosDao
    abstract fun getTodosDao(): TodosDao
}