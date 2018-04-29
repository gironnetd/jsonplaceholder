package com.vlabs.jsonplaceholder.instrumentation.injection.module

import android.app.Application
import android.content.Context
import com.nhaarman.mockito_kotlin.mock
import com.vlabs.jsonplaceholder.UiThread
import com.vlabs.jsonplaceholder.cache.PreferencesHelper
import com.vlabs.jsonplaceholder.data.executor.JobExecutor
import com.vlabs.jsonplaceholder.data.repositories.albums.AlbumCache
import com.vlabs.jsonplaceholder.data.repositories.albums.AlbumRemote
import com.vlabs.jsonplaceholder.data.repositories.comments.CommentCache
import com.vlabs.jsonplaceholder.data.repositories.comments.CommentRemote
import com.vlabs.jsonplaceholder.data.repositories.photos.PhotoCache
import com.vlabs.jsonplaceholder.data.repositories.photos.PhotoRemote
import com.vlabs.jsonplaceholder.data.repositories.posts.PostCache
import com.vlabs.jsonplaceholder.data.repositories.posts.PostRemote
import com.vlabs.jsonplaceholder.data.repositories.todos.TodoCache
import com.vlabs.jsonplaceholder.data.repositories.todos.TodoRemote
import com.vlabs.jsonplaceholder.data.repositories.users.UserCache
import com.vlabs.jsonplaceholder.data.repositories.users.UserRemote
import com.vlabs.jsonplaceholder.domain.executor.PostExecutionThread
import com.vlabs.jsonplaceholder.domain.executor.ThreadExecutor
import com.vlabs.jsonplaceholder.domain.repositories.albums.AlbumRepository
import com.vlabs.jsonplaceholder.domain.repositories.comments.CommentRepository
import com.vlabs.jsonplaceholder.domain.repositories.photos.PhotoRepository
import com.vlabs.jsonplaceholder.domain.repositories.posts.PostRepository
import com.vlabs.jsonplaceholder.domain.repositories.todos.TodoRepository
import com.vlabs.jsonplaceholder.domain.repositories.users.UserRepository
import com.vlabs.jsonplaceholder.injection.scopes.PerApplication
import com.vlabs.jsonplaceholder.remote.remote.albums.AlbumService
import com.vlabs.jsonplaceholder.remote.remote.comments.CommentService
import com.vlabs.jsonplaceholder.remote.remote.photos.PhotoService
import com.vlabs.jsonplaceholder.remote.remote.posts.PostService
import com.vlabs.jsonplaceholder.remote.remote.todos.TodoService
import com.vlabs.jsonplaceholder.remote.remote.users.UserService
import dagger.Module
import dagger.Provides

@Module
class TestApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun providePreferencesHelper(): PreferencesHelper {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideUserRepository(): UserRepository {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideUserCache(): UserCache {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideUserRemote(): UserRemote {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun providePostRepository(): PostRepository {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun providePostCache(): PostCache {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun providePostRemote(): PostRemote {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideAlbumRepository(): AlbumRepository {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideAlbumCache(): AlbumCache {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideAlbumRemote(): AlbumRemote {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideTodoRepository(): TodoRepository {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideTodoCache(): TodoCache {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideTodoRemote(): TodoRemote {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideCommentRepository(): CommentRepository {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideCommentCache(): CommentCache {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideCommentRemote(): CommentRemote {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun providePhotoRepository(): PhotoRepository {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun providePhotoCache(): PhotoCache {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun providePhotoRemote(): PhotoRemote {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @PerApplication
    internal fun providePostService(): PostService {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideUserService(): UserService {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideAlbumService(): AlbumService {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideTodoService(): TodoService {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideCommentService(): CommentService {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun providePhotoService(): PhotoService {
        return mock()
    }


}