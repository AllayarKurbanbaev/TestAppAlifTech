package uz.akfadiler.testappaliftech.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.akfadiler.testappaliftech.data.local.database.AppDatabase
import uz.akfadiler.testappaliftech.data.local.database.photos.PhotosDao
import uz.akfadiler.testappaliftech.data.local.database.posts.PostsDao
import uz.akfadiler.testappaliftech.data.local.database.todos.TodosDao
import uz.akfadiler.testappaliftech.data.local.database.users.UserDao
import uz.akfadiler.testappaliftech.data.local.sharedpreference.AppSharedPreference
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @[Provides Singleton]
    fun getDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "AppDatabase")
            .fallbackToDestructiveMigration().build()

    @[Provides Singleton]
    fun getUserDao(database: AppDatabase): UserDao = database.getUserDao()

    @[Provides Singleton]
    fun getPostsDao(database: AppDatabase): PostsDao = database.getPostsDao()

    @[Provides Singleton]
    fun getPhotosDao(database: AppDatabase): PhotosDao = database.getPhotosDao()

    @[Provides Singleton]
    fun getTodosDao(database: AppDatabase): TodosDao = database.getTodosDao()

    @[Provides Singleton]
    fun getSharedPreference(@ApplicationContext context: Context): AppSharedPreference =
        AppSharedPreference(context)
}