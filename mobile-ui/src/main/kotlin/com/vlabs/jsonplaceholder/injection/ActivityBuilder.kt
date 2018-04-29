package com.vlabs.jsonplaceholder.injection

import com.vlabs.jsonplaceholder.ui.userdetails.UserDetailsActivity
import com.vlabs.jsonplaceholder.ui.userdetails.fragments.UserDetailsFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by mertsimsek on 25/05/2017.
 */
//@Module
abstract class ActivityBuilder {

    //@ContributesAndroidInjector(modules = arrayOf(UserDetailsFragmentProvider::class))
    internal abstract fun bindTableContentsActivity(): UserDetailsActivity
//
//    @ContributesAndroidInjector(modules = arrayOf(ContentsActivityModule::class))
//    internal abstract fun bindContentsActivity(): ContentsActivity
//
//    @ContributesAndroidInjector(modules = arrayOf(FavoritesActivityModule::class))
//    internal abstract fun bindFavoritesActivity(): FavoritesActivity
//
//    @ContributesAndroidInjector(modules = arrayOf(BiographyActivityModule::class, BiographyFragmentProvider::class))
//    internal abstract fun bindBiographyActivity(): BiographyActivity
//
//    @ContributesAndroidInjector(modules = arrayOf(SettingsActivityModule::class, SettingsFragmentProvider::class))
//    internal abstract fun bindSettingsActivity(): SettingsActivity
}
