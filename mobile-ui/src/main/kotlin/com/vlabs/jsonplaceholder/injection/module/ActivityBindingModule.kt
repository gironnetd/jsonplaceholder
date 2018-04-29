package com.vlabs.jsonplaceholder.injection.module

import com.vlabs.jsonplaceholder.injection.scopes.PerActivity
import com.vlabs.jsonplaceholder.injection.scopes.PerFragment
import com.vlabs.jsonplaceholder.ui.comments.CommentsListActivity
import com.vlabs.jsonplaceholder.ui.photos.PhotosListActivity
import com.vlabs.jsonplaceholder.ui.userdetails.UserDetailsActivity
import com.vlabs.jsonplaceholder.ui.userdetails.fragments.UserDetailsFragmentProvider
import com.vlabs.jsonplaceholder.ui.userdetails.fragments.album.AlbumFragment
import com.vlabs.jsonplaceholder.ui.userdetails.fragments.post.PostFragment
import com.vlabs.jsonplaceholder.ui.userdetails.fragments.todo.TodoFragment
import com.vlabs.jsonplaceholder.ui.users.UsersListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

//    @PerActivity
//    @ContributesAndroidInjector(modules = arrayOf(BrowseActivityModule::class))
//    abstract fun bindPostsActivity(): BrowsePostsActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(BrowseActivityModule::class))
    abstract fun bindUsersListActivity(): UsersListActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(BrowseActivityModule::class, UserDetailsFragmentProvider::class))
    abstract fun bindUserDetailsActivity(): UserDetailsActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(BrowseActivityModule::class))
    abstract fun bindPhotosListActivity(): PhotosListActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(BrowseActivityModule::class))
    abstract fun bindCommentsListActivity(): CommentsListActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(BrowseActivityModule::class))
    abstract fun bindAlbumsFragment(): AlbumFragment

    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(BrowseActivityModule::class))
    abstract fun bindPostsFragment(): PostFragment

    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(BrowseActivityModule::class))
    abstract fun bindTodosFragment(): TodoFragment



}