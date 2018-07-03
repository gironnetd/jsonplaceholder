package com.vlabs.jsonplaceholder.ui.userdetails.fragments

import com.vlabs.jsonplaceholder.ui.userdetails.fragments.album.AlbumFragment
import com.vlabs.jsonplaceholder.ui.userdetails.fragments.album.AlbumFragmentModule
import com.vlabs.jsonplaceholder.ui.userdetails.fragments.post.PostFragment
import com.vlabs.jsonplaceholder.ui.userdetails.fragments.post.PostFragmentModule
import com.vlabs.jsonplaceholder.ui.userdetails.fragments.todo.TodoFragment
import com.vlabs.jsonplaceholder.ui.userdetails.fragments.todo.TodoFragmentModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by damien on 12/03/2018.
 */

@Module
abstract class UserDetailsFragmentProvider {

    @ContributesAndroidInjector(modules = arrayOf(AlbumFragmentModule::class))
    internal abstract fun provideAlbumFragment(): AlbumFragment

    @ContributesAndroidInjector(modules = arrayOf(PostFragmentModule::class))
    internal abstract fun providePostFragment(): PostFragment

    @ContributesAndroidInjector(modules = arrayOf(TodoFragmentModule::class))
    internal abstract fun provideTodoFragment(): TodoFragment
}
