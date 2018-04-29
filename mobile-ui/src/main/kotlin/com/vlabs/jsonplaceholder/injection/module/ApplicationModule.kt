package com.vlabs.jsonplaceholder.injection.module

import android.app.Application
import android.content.Context
//import android.support.annotation.UiThread
import com.vlabs.jsonplaceholder.cache.PreferencesHelper
import com.vlabs.jsonplaceholder.cache.db.DbOpenHelper
import com.vlabs.jsonplaceholder.cache.mapper.posts.PostEntityMapper
import com.vlabs.jsonplaceholder.cache.mapper.users.UserEntityMapper
import dagger.Module
import dagger.Provides
import com.vlabs.jsonplaceholder.cache.model.users.PostCacheImpl
import com.vlabs.jsonplaceholder.cache.model.users.UserCacheImpl
import com.vlabs.jsonplaceholder.data.executor.JobExecutor
import com.vlabs.jsonplaceholder.data.mappers.posts.PostMapper
import com.vlabs.jsonplaceholder.data.mappers.users.UserMapper
import com.vlabs.jsonplaceholder.data.repositories.posts.PostRemote
import com.vlabs.jsonplaceholder.data.repositories.users.UserCache
import com.vlabs.jsonplaceholder.data.repositories.users.UserRemote
import com.vlabs.jsonplaceholder.data.sources.posts.PostDataRepository
import com.vlabs.jsonplaceholder.data.sources.posts.PostDataStoreFactory
import com.vlabs.jsonplaceholder.data.sources.users.UserDataRepository
import com.vlabs.jsonplaceholder.data.sources.users.UserDataStoreFactory
import com.vlabs.jsonplaceholder.domain.executor.PostExecutionThread
import com.vlabs.jsonplaceholder.domain.executor.ThreadExecutor
import com.vlabs.jsonplaceholder.domain.repositories.posts.PostRepository
import com.vlabs.jsonplaceholder.domain.repositories.users.UserRepository
import com.vlabs.jsonplaceholder.remote.*
import com.vlabs.jsonplaceholder.remote.remote.posts.PostRemoteImpl
import com.vlabs.jsonplaceholder.remote.remote.posts.PostService
import com.vlabs.jsonplaceholder.remote.remote.users.UserRemoteImpl
import com.vlabs.jsonplaceholder.remote.remote.users.UserService
import com.vlabs.jsonplaceholder.BuildConfig
import com.vlabs.jsonplaceholder.UiThread
import com.vlabs.jsonplaceholder.cache.model.albums.AlbumCacheImpl
import com.vlabs.jsonplaceholder.cache.model.comments.CommentCacheImpl
import com.vlabs.jsonplaceholder.cache.model.photos.PhotoCacheImpl
import com.vlabs.jsonplaceholder.cache.model.todos.TodoCacheImpl
import com.vlabs.jsonplaceholder.data.mappers.albums.AlbumEntityMapper
import com.vlabs.jsonplaceholder.data.mappers.albums.AlbumMapper
import com.vlabs.jsonplaceholder.data.mappers.comments.CommentEntityMapper
import com.vlabs.jsonplaceholder.data.mappers.comments.CommentMapper
import com.vlabs.jsonplaceholder.data.mappers.photos.PhotoEntityMapper
import com.vlabs.jsonplaceholder.data.mappers.photos.PhotoMapper
import com.vlabs.jsonplaceholder.data.mappers.todos.TodoEntityMapper
import com.vlabs.jsonplaceholder.data.mappers.todos.TodoMapper
import com.vlabs.jsonplaceholder.data.repositories.albums.AlbumCache
import com.vlabs.jsonplaceholder.data.repositories.albums.AlbumRemote
import com.vlabs.jsonplaceholder.data.repositories.comments.CommentCache
import com.vlabs.jsonplaceholder.data.repositories.comments.CommentRemote
import com.vlabs.jsonplaceholder.data.repositories.photos.PhotoCache
import com.vlabs.jsonplaceholder.data.repositories.photos.PhotoRemote
import com.vlabs.jsonplaceholder.data.repositories.posts.PostCache
import com.vlabs.jsonplaceholder.data.repositories.todos.TodoCache
import com.vlabs.jsonplaceholder.data.repositories.todos.TodoRemote
import com.vlabs.jsonplaceholder.data.sources.albums.AlbumDataRepository
import com.vlabs.jsonplaceholder.data.sources.albums.AlbumDataStoreFactory
import com.vlabs.jsonplaceholder.data.sources.comments.CommentDataRepository
import com.vlabs.jsonplaceholder.data.sources.comments.CommentDataStoreFactory
import com.vlabs.jsonplaceholder.data.sources.photos.PhotoDataRepository
import com.vlabs.jsonplaceholder.data.sources.photos.PhotoDataStoreFactory
import com.vlabs.jsonplaceholder.data.sources.todos.TodoDataRepository
import com.vlabs.jsonplaceholder.data.sources.todos.TodoDataStoreFactory
import com.vlabs.jsonplaceholder.domain.repositories.albums.AlbumRepository
import com.vlabs.jsonplaceholder.domain.repositories.comments.CommentRepository
import com.vlabs.jsonplaceholder.domain.repositories.photos.PhotoRepository
import com.vlabs.jsonplaceholder.domain.repositories.todos.TodoRepository
import com.vlabs.jsonplaceholder.injection.scopes.PerApplication
import com.vlabs.jsonplaceholder.remote.remote.albums.AlbumRemoteImpl
import com.vlabs.jsonplaceholder.remote.remote.albums.AlbumService
import com.vlabs.jsonplaceholder.remote.remote.albums.AlbumServiceImpl
import com.vlabs.jsonplaceholder.remote.remote.comments.CommentRemoteImpl
import com.vlabs.jsonplaceholder.remote.remote.comments.CommentService
import com.vlabs.jsonplaceholder.remote.remote.comments.CommentServiceImpl
import com.vlabs.jsonplaceholder.remote.remote.photos.PhotoRemoteImpl
import com.vlabs.jsonplaceholder.remote.remote.photos.PhotoService
import com.vlabs.jsonplaceholder.remote.remote.photos.PhotoServiceImpl
import com.vlabs.jsonplaceholder.remote.remote.todos.TodoRemoteImpl
import com.vlabs.jsonplaceholder.remote.remote.todos.TodoService
import com.vlabs.jsonplaceholder.remote.remote.todos.TodoServiceImpl


//import org.buffer.android.boilerplate.cache.PreferencesHelper
//import org.buffer.android.boilerplate.cache.db.DbOpenHelper
//import org.buffer.android.boilerplate.cache.mapper.BufferooEntityMapper
//import org.buffer.android.boilerplate.data.AlbumDataRepository
//import org.buffer.android.boilerplate.data.executor.JobExecutor
//import org.buffer.android.boilerplate.data.mapper.BufferooMapper
//import org.buffer.android.boilerplate.data.repository.BufferooCache
//import org.buffer.android.boilerplate.data.source.AlbumDataStoreFactory
//import org.buffer.android.boilerplate.remote.UserRemoteImpl
//import org.buffer.android.boilerplate.remote.UserService
//import org.buffer.android.boilerplate.remote.Service

/**
 * Module used to provide dependencies at an application-level.
 */
@Module
open class ApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun providePreferencesHelper(context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }


    @Provides
    @PerApplication
    internal fun provideUserRepository(factory: UserDataStoreFactory,
                                           mapper: UserMapper): UserRepository {
        return UserDataRepository(factory, mapper)
    }

    @Provides
    @PerApplication
    internal fun provideUserCache(factory: DbOpenHelper,
                                      entityMapper: UserEntityMapper,
                                      mapper: com.vlabs.jsonplaceholder.cache.db.mapper.users.UserMapper,
                                      helper: PreferencesHelper): UserCache {
        return UserCacheImpl(factory, entityMapper, mapper, helper)
    }

    @Provides
    @PerApplication
    internal fun provideUserRemote(service: UserService,
                                       factory: com.vlabs.jsonplaceholder.remote.mappers.users.UserEntityMapper): UserRemote {
        return UserRemoteImpl(service, factory)
    }

    @Provides
    @PerApplication
    internal fun providePostRepository(factory: PostDataStoreFactory,
                                       mapper: PostMapper): PostRepository {
        return PostDataRepository(factory, mapper)
    }

    @Provides
    @PerApplication
    internal fun providePostCache(factory: DbOpenHelper,
                                  entityMapper: PostEntityMapper,
                                  mapper: com.vlabs.jsonplaceholder.cache.db.mapper.posts.PostMapper,
                                  helper: PreferencesHelper): PostCache {
        return PostCacheImpl(factory, entityMapper, mapper, helper)
    }

    @Provides
    @PerApplication
    internal fun providePostRemote(service: PostService,
                                   factory: com.vlabs.jsonplaceholder.remote.mappers.posts.PostEntityMapper): PostRemote {
        return PostRemoteImpl(service, factory)
    }

    @Provides
    @PerApplication
    internal fun provideAlbumRepository(factory: AlbumDataStoreFactory,
                                       mapper: AlbumMapper): AlbumRepository {
        return AlbumDataRepository(factory, mapper)
    }

    @Provides
    @PerApplication
    internal fun provideAlbumCache(factory: DbOpenHelper,
                                  entityMapper: AlbumEntityMapper,
                                  mapper: com.vlabs.jsonplaceholder.cache.db.mapper.albums.AlbumMapper,
                                  helper: PreferencesHelper): AlbumCache {
        return AlbumCacheImpl(factory, entityMapper, mapper, helper)
    }

    @Provides
    @PerApplication
    internal fun provideAlbumRemote(service: AlbumService,
                                   factory: com.vlabs.jsonplaceholder.remote.mappers.albums.AlbumEntityMapper): AlbumRemote {
        return AlbumRemoteImpl(service, factory)
    }

    @Provides
    @PerApplication
    internal fun provideTodoRepository(factory: TodoDataStoreFactory,
                                        mapper: TodoMapper): TodoRepository {
        return TodoDataRepository(factory, mapper)
    }

    @Provides
    @PerApplication
    internal fun provideTodoCache(factory: DbOpenHelper,
                                   entityMapper: TodoEntityMapper,
                                   mapper: com.vlabs.jsonplaceholder.cache.db.mapper.todos.TodoMapper,
                                   helper: PreferencesHelper): TodoCache {
        return TodoCacheImpl(factory, entityMapper, mapper, helper)
    }

    @Provides
    @PerApplication
    internal fun provideTodoRemote(service: TodoService,
                                    factory: com.vlabs.jsonplaceholder.remote.mappers.todos.TodoEntityMapper
    ): TodoRemote {
        return TodoRemoteImpl(service, factory)
    }

    @Provides
    @PerApplication
    internal fun provideCommentRepository(factory: CommentDataStoreFactory,
                                       mapper: CommentMapper): CommentRepository {
        return CommentDataRepository(factory, mapper)
    }

    @Provides
    @PerApplication
    internal fun provideCommentCache(factory: DbOpenHelper,
                                  entityMapper: CommentEntityMapper,
                                  mapper: com.vlabs.jsonplaceholder.cache.db.mapper.comments.CommentMapper,
                                  helper: PreferencesHelper): CommentCache {
        return CommentCacheImpl(factory, entityMapper, mapper, helper)
    }

    @Provides
    @PerApplication
    internal fun provideCommentRemote(service: CommentService,
                                   factory: com.vlabs.jsonplaceholder.remote.mappers.comments.CommentEntityMapper): CommentRemote {
        return CommentRemoteImpl(service, factory)
    }

    @Provides
    @PerApplication
    internal fun providePhotoRepository(factory: PhotoDataStoreFactory,
                                          mapper: PhotoMapper): PhotoRepository {
        return PhotoDataRepository(factory, mapper)
    }

    @Provides
    @PerApplication
    internal fun providePhotoCache(factory: DbOpenHelper,
                                     entityMapper: PhotoEntityMapper,
                                     mapper: com.vlabs.jsonplaceholder.cache.db.mapper.photos.PhotoMapper,
                                     helper: PreferencesHelper): PhotoCache {
        return PhotoCacheImpl(factory, entityMapper, mapper, helper)
    }

    @Provides
    @PerApplication
    internal fun providePhotoRemote(service: PhotoService,
                                      factory: com.vlabs.jsonplaceholder.remote.mappers.photos.PhotoEntityMapper): PhotoRemote {
        return PhotoRemoteImpl(service, factory)
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
        return PostServiceImpl.makeService(BuildConfig.DEBUG)
    }

    @Provides
    @PerApplication
    internal fun provideUserService(): UserService {
        return UserServiceImpl.makeService(BuildConfig.DEBUG)
    }

    @Provides
    @PerApplication
    internal fun provideAlbumService(): AlbumService {
        return AlbumServiceImpl.makeService(BuildConfig.DEBUG)
    }

    @Provides
    @PerApplication
    internal fun provideTodoService(): TodoService {
        return TodoServiceImpl.makeService(BuildConfig.DEBUG)
    }

    @Provides
    @PerApplication
    internal fun provideCommentService(): CommentService {
        return CommentServiceImpl.makeService(BuildConfig.DEBUG)
    }

    @Provides
    @PerApplication
    internal fun providePhotoService(): PhotoService {
        return PhotoServiceImpl.makeService(BuildConfig.DEBUG)
    }
}
