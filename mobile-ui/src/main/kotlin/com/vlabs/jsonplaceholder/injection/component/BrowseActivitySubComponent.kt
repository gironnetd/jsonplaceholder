package com.vlabs.jsonplaceholder.injection.component

import com.vlabs.jsonplaceholder.ui.userdetails.UserDetailsActivity
import com.vlabs.jsonplaceholder.ui.users.UsersListActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface BrowseActivitySubComponent : AndroidInjector<UsersListActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<UsersListActivity>()

}