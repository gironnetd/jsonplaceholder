package com.vlabs.jsonplaceholder.ui.userdetails.fragments.album

import com.vlabs.jsonplaceholder.domain.interactors.albums.GetAlbumsByUserId
import com.vlabs.jsonplaceholder.injection.scopes.PerFragment
import com.vlabs.jsonplaceholer.presentation.mapper.albums.AlbumMapper
import com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.album.AlbumsContract
import com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.album.AlbumsPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by damien on 12/03/2018.
 */

@Module
class AlbumFragmentModule {

    //@PerFragment
    @Provides
    internal fun provideAlbumsView(albumsFragment: AlbumFragment): AlbumsContract.View {
        return albumsFragment
    }

    //@PerFragment
    @Provides
    internal fun provideAlbumsPresenter(mainView: AlbumsContract.View,
                                        getAlbumsByUserId: GetAlbumsByUserId
                                        , albumMapper: AlbumMapper):
            AlbumsContract.Presenter {
        return AlbumsPresenter(mainView, getAlbumsByUserId, albumMapper)
    }

}//    @Provides
//    MailContract.View provideAuthorsFragment(MailFragment mailFragment){
//        return mailFragment;
//    }
//
//    @Provides
//    MailContract.Presenter provideAuthorsPresenter(MailPresenter mailPresenter) {
//        return  mailPresenter;
//    }
