package com.vlabs.jsonplaceholder.ui.userdetails.fragments.post

import com.vlabs.jsonplaceholder.domain.interactors.posts.GetPostsByUserId
import com.vlabs.jsonplaceholder.injection.scopes.PerActivity
import com.vlabs.jsonplaceholder.injection.scopes.PerFragment
import com.vlabs.jsonplaceholer.presentation.mapper.posts.PostMapper
import com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.post.PostsContract
import com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.post.PostsPresenter
import dagger.Module
import dagger.Provides

@Module
class PostFragmentModule {

    //@PerFragment
    @Provides
    internal fun providePostsView(postsFragment: PostFragment): PostsContract.View {
        return postsFragment
    }

    //@PerFragment
    @Provides
    internal fun providePostsPresenter(mainView: PostsContract.View,
                                        getPostsByUserId: GetPostsByUserId
                                        , postMapper: PostMapper):
            PostsContract.Presenter {
        return PostsPresenter(mainView, getPostsByUserId, postMapper)
    }
}