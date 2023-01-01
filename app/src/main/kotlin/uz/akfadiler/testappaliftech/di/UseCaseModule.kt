package uz.akfadiler.testappaliftech.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.akfadiler.testappaliftech.domain.usecase.*
import uz.akfadiler.testappaliftech.domain.usecase.impl.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @[Binds Singleton]
    fun getUserListUseCase(impl: GetUserListUseCaseImpl): GetUserListUseCase

    @[Binds Singleton]
    fun getUserByIdUseCase(impl: GetUserByIdUseCaseImpl): GetUserByIdUseCase

    @[Binds Singleton]
    fun getPostListUseCase(impl: GetPostListUseCaseImpl): GetPostListUseCase

    @[Binds Singleton]
    fun getPostByIdUseCase(impl: GetPostByIdUseCaseImpl): GetPostByIdUseCase

    @[Binds Singleton]
    fun getPostByUserIdUseCase(impl: GetPostByUserIdUseCaseImpl): GetPostByUserIdUseCase

    @[Binds Singleton]
    fun deletePostByIdUseCase(impl: DeletePostByIdUseCaseImpl): DeletePostByIdUseCase
}