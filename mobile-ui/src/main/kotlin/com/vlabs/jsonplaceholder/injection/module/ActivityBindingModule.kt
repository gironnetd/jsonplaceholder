package com.vlabs.jsonplaceholder.injection.module

import com.vlabs.jsonplaceholder.injection.scopes.PerActivity
import com.vlabs.jsonplaceholder.ui.comments.CommentsListActivity
import com.vlabs.jsonplaceholder.ui.photos.PhotosListActivity
import com.vlabs.jsonplaceholder.ui.userdetails.UserDetailsActivity
import com.vlabs.jsonplaceholder.ui.userdetails.fragments.UserDetailsFragmentProvider
import com.vlabs.jsonplaceholder.ui.users.UsersListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(ActivityModule::class))
    abstract fun bindUsersListActivity(): UsersListActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(ActivityModule::class,UserDetailsFragmentProvider::class))
    abstract fun bindUserDetailsActivity(): UserDetailsActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(ActivityModule::class))
    abstract fun bindPhotosListActivity(): PhotosListActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(ActivityModule::class))
    abstract fun bindCommentsListActivity(): CommentsListActivity
}