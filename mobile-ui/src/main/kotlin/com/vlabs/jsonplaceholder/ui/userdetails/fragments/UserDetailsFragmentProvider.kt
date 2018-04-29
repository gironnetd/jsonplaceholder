package com.vlabs.jsonplaceholder.ui.userdetails.fragments

import com.vlabs.jsonplaceholder.ui.userdetails.fragments.album.AlbumFragment
import com.vlabs.jsonplaceholder.ui.userdetails.fragments.album.AlbumFragmentModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by damien on 12/03/2018.
 */

@Module
abstract class UserDetailsFragmentProvider {

    @ContributesAndroidInjector(modules = arrayOf(AlbumFragmentModule::class))
    internal abstract fun provideAlbumFragment(): AlbumFragment

    //    @ContributesAndroidInjector(modules = MovementsFragmentModule.class)
    //    abstract MovementsFragment provideMovementsFragment();
    //
    //    @ContributesAndroidInjector(modules = NotificationsFragmentModule.class)
    //    abstract NotificationsFragment provideNotificationsFragment();
    //
    //    @ContributesAndroidInjector(modules = TipsFragmentModule.class)
    //    abstract TipsFragment provideTipsFragment();
    //
    //    @ContributesAndroidInjector(modules = TypefacesFragmentModule.class)
    //    abstract TypefacesFragment provideTypefacesFragment();
}
