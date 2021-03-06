package com.vlabs.jsonplaceholder.injection.module

import com.vlabs.jsonplaceholder.domain.interactors.albums.GetAlbumsByUserId
import com.vlabs.jsonplaceholder.domain.interactors.comments.GetCommentsByPostId
import com.vlabs.jsonplaceholder.domain.interactors.photos.GetPhotosByAlbumId
import com.vlabs.jsonplaceholder.domain.interactors.posts.GetPostsByUserId
import com.vlabs.jsonplaceholder.domain.interactors.todos.GetTodosByUserId
import com.vlabs.jsonplaceholder.domain.interactors.users.GetUsers
import com.vlabs.jsonplaceholder.presentation.mapper.users.UserMapper
import com.vlabs.jsonplaceholder.injection.scopes.PerActivity
import com.vlabs.jsonplaceholder.injection.scopes.PerFragment
import com.vlabs.jsonplaceholder.presentation.ui.posts.UsersListContract
import com.vlabs.jsonplaceholder.presentation.ui.posts.UsersListPresenter
import com.vlabs.jsonplaceholder.ui.comments.CommentsListActivity
import com.vlabs.jsonplaceholder.ui.photos.PhotosListActivity
import com.vlabs.jsonplaceholder.ui.userdetails.UserDetailsActivity
import com.vlabs.jsonplaceholder.ui.userdetails.fragments.album.AlbumFragment
import com.vlabs.jsonplaceholder.ui.userdetails.fragments.post.PostFragment
import com.vlabs.jsonplaceholder.ui.userdetails.fragments.todo.TodoFragment
import com.vlabs.jsonplaceholder.ui.users.UsersListActivity
import com.vlabs.jsonplaceholer.presentation.mapper.albums.AlbumMapper
import com.vlabs.jsonplaceholer.presentation.mapper.comments.CommentMapper
import com.vlabs.jsonplaceholer.presentation.mapper.photos.PhotoMapper
import com.vlabs.jsonplaceholer.presentation.mapper.posts.PostMapper
import com.vlabs.jsonplaceholer.presentation.mapper.todos.TodoMapper
import com.vlabs.jsonplaceholer.presentation.ui.comments.CommentsContract
import com.vlabs.jsonplaceholer.presentation.ui.comments.CommentsPresenter
import com.vlabs.jsonplaceholer.presentation.ui.photos.PhotosContract
import com.vlabs.jsonplaceholer.presentation.ui.photos.PhotosPresenter
import com.vlabs.jsonplaceholer.presentation.ui.userdetails.UserDetailsContract
import com.vlabs.jsonplaceholer.presentation.ui.userdetails.UsersDetailsPresenter
import com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.album.AlbumsContract
import com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.album.AlbumsPresenter
import com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.post.PostsContract
import com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.post.PostsPresenter
import com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.todo.TodosContract
import com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.todo.TodosPresenter
import dagger.Module
import dagger.Provides

//import org.buffer.android.boilerplate.presentation.browse.UsersListPresenter
//import org.buffer.android.boilerplate.presentation.mapper.UserMapper
//import org.buffer.android.boilerplate.ui.browse.BrowsePostsActivity



/**
 * Module used to provide dependencies at an activity-level.
 */
@Module
open class ActivityModule {

    @PerActivity
    @Provides
    internal fun provideBrowseView(usersListActivity: UsersListActivity): UsersListContract.View {
        return usersListActivity
    }

    @PerActivity
    @Provides
    internal fun provideBrowsePresenter(mainView: UsersListContract.View,
                                        getUsers: GetUsers, mapper: UserMapper): UsersListContract.Presenter {
        return UsersListPresenter(mainView, getUsers, mapper)
    }

    @PerActivity
    @Provides
    internal fun providePhotoView(photosListActivity: PhotosListActivity): PhotosContract.View {
        return photosListActivity
    }

    @PerActivity
    @Provides
    internal fun providePhotoPresenter(mainView: PhotosContract.View,
                                       getPhotosByAlbumId: GetPhotosByAlbumId, mapper: PhotoMapper):
            PhotosContract.Presenter {
        return PhotosPresenter(mainView, getPhotosByAlbumId, mapper)
    }

    @PerActivity
    @Provides
    internal fun provideCommentView(commentsListActivity: CommentsListActivity): CommentsContract.View {
        return commentsListActivity
    }

    @PerActivity
    @Provides
    internal fun provideCommentPresenter(mainView: CommentsContract.View,
                                         getCommentsByPostId: GetCommentsByPostId, mapper: CommentMapper):
            CommentsContract.Presenter {
        return CommentsPresenter(mainView, getCommentsByPostId, mapper)
    }

    @PerActivity
    @Provides
    internal fun provideUserDetailsView(userDetailsActivity: UserDetailsActivity): UserDetailsContract.View {
        return userDetailsActivity
    }

    @PerActivity
    @Provides
    internal fun provideUserDetailsPresenter(mainView: UserDetailsContract.View): UserDetailsContract.Presenter {
        return UsersDetailsPresenter(mainView)
    }
}
