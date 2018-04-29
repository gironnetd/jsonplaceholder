package com.vlabs.jsonplaceholder.instrumentation.injection.component

import android.app.Application
import com.vlabs.jsonplaceholder.domain.executor.PostExecutionThread
import com.vlabs.jsonplaceholder.domain.repositories.users.UserRepository
import com.vlabs.jsonplaceholder.injection.ApplicationComponent
import com.vlabs.jsonplaceholder.injection.module.ActivityBindingModule
import com.vlabs.jsonplaceholder.injection.scopes.PerApplication
import com.vlabs.jsonplaceholder.instrumentation.injection.module.TestApplicationModule
import com.vlabs.jsonplaceholder.instrumentation.test.TestApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = arrayOf(TestApplicationModule::class, ActivityBindingModule::class,
        AndroidSupportInjectionModule::class))
@PerApplication
interface TestApplicationComponent : ApplicationComponent {

    fun userRepository(): UserRepository

    fun postExecutionThread(): PostExecutionThread

    fun inject(application: TestApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): TestApplicationComponent.Builder

        fun build(): TestApplicationComponent
    }

}