package com.vlabs.jsonplaceholder.injection

import android.app.Application
import com.vlabs.jsonplaceholder.JsonPlaceholderApplication
import com.vlabs.jsonplaceholder.injection.module.ActivityBindingModule
import com.vlabs.jsonplaceholder.injection.module.ApplicationModule
import com.vlabs.jsonplaceholder.injection.scopes.PerApplication
import com.vlabs.jsonplaceholder.ui.userdetails.fragments.album.AlbumFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = arrayOf(ActivityBindingModule::class, ApplicationModule::class,
        AndroidSupportInjectionModule::class))
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(app: JsonPlaceholderApplication)
    //fun inject(albumFragment: AlbumFragment)
}
