package com.vlabs.jsonplaceholder.ui.photos

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_users_list.*
import com.vlabs.jsonplaceholder.presentation.model.UserView
import com.vlabs.jsonplaceholder.R
import com.vlabs.jsonplaceholder.mappers.PhotoMapper
import com.vlabs.jsonplaceholder.mappers.UserMapper
import com.vlabs.jsonplaceholder.presentation.ui.posts.UsersListContract
import com.vlabs.jsonplaceholer.presentation.model.ALBUM_ID_KEY
import com.vlabs.jsonplaceholer.presentation.model.PhotoView
import com.vlabs.jsonplaceholer.presentation.ui.photos.PhotosContract
import kotlinx.android.synthetic.main.activity_photos.*
import javax.inject.Inject

class PhotosListActivity: AppCompatActivity(), PhotosContract.View {

    @Inject lateinit var onboardingPresenter: PhotosContract.Presenter
    @Inject lateinit var photosListAdapter: PhotosListAdapter
    @Inject lateinit var mapper: PhotoMapper
    var albumId: Int = -1

    override fun setPresenter(presenter: PhotosContract.Presenter) {
        onboardingPresenter = presenter
    }

    override fun hideProgress() {
        //users_list_progress.visibility = View.VISIBLE
    }

    override fun showProgress() {
        //users_list_progress.visibility = View.GONE
    }

    override fun showPhotos(photos: List<PhotoView>) {
        photosListAdapter.photos = photos.map { mapper.mapToViewModel(it) }
        photosListAdapter.notifyDataSetChanged()
        recycler_photos.visibility = View.VISIBLE
    }

    override fun hidePhotos() {
        recycler_photos.visibility = View.VISIBLE
    }

    override fun showErrorState() {
    }

    override fun hideErrorState() {
    }

    override fun showEmptyState() {
    }

    override fun hideEmptyState() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)
        AndroidInjection.inject(this)
        albumId = intent.getIntExtra(ALBUM_ID_KEY, -1)
        setupBrowseRecycler()
    }

    override fun onStart() {
        super.onStart()
        onboardingPresenter.start(albumId)
    }

    private fun setupBrowseRecycler() {
        recycler_photos.layoutManager = LinearLayoutManager(this)
        recycler_photos.adapter = photosListAdapter
    }
}