package com.vlabs.jsonplaceholder.ui.userdetails.fragments.album

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vlabs.jsonplaceholder.R
import com.vlabs.jsonplaceholder.mappers.AlbumMapper
import com.vlabs.jsonplaceholder.ui.userdetails.AlbumsAdapter
import com.vlabs.jsonplaceholder.ui.userdetails.UserDetailsActivity
import com.vlabs.jsonplaceholer.presentation.model.AlbumView
import com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.album.AlbumsContract
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_albums_list.view.*
import kotlinx.android.synthetic.main.fragment_users_details.view.*
import javax.inject.Inject

class AlbumFragment : Fragment(), AlbumsContract.View  {


    override fun showAlbums(albums: List<AlbumView>) {

        albumsAdapter.albums = albums.map { albumMapper.mapToViewModel(it) }
        albumsAdapter.notifyDataSetChanged()
        //recycler_albums.visibility = View.VISIBLE
    }

    override fun hideAlbums() {
        //recycler_albums.visibility = View.VISIBLE
    }

    override fun showErrorState() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideErrorState() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEmptyState() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideEmptyState() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPresenter(presenter: AlbumsContract.Presenter) {
        onboardingPresenter = presenter
    }

    override fun onStart() {
        super.onStart()
    //    onboardingPresenter.start()
    }

    override fun onResume() {
        super.onResume()
    //    onboardingPresenter?.start()
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        onboardingPresenter.start()
//    }

    @Inject lateinit var onboardingPresenter: AlbumsContract.Presenter
    @Inject lateinit var albumsAdapter: AlbumsAdapter
    @Inject lateinit var albumMapper: AlbumMapper
    lateinit var albums: List<AlbumView>
//    override fun onAttach(context: Context?) {
//        AndroidSupportInjection.inject(this)
//        super.onAttach(context)
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_albums_list, container, false)
        //rootView.section_label.text = "Album fragment"
        rootView.recycler_albums.layoutManager = LinearLayoutManager(activity)
        //albumMapper = AlbumMapper
        albumMapper = AlbumMapper()
        albumsAdapter = AlbumsAdapter(context)
        rootView.recycler_albums.adapter = albumsAdapter

        albumsAdapter.albums = albums.map { albumMapper.mapToViewModel(it) }
        albumsAdapter.notifyDataSetChanged()
        //rootView.recycler_albums.adapter = albumsAdapter
        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(albums: List<AlbumView>): AlbumFragment {
            val fragment = AlbumFragment()
            val args = Bundle()
            //args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            fragment.albums = albums
            return fragment
        }
    }
}