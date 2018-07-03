package com.vlabs.jsonplaceholder.ui.userdetails.fragments.post

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vlabs.jsonplaceholder.R
import com.vlabs.jsonplaceholder.mappers.PostMapper
import com.vlabs.jsonplaceholder.ui.userdetails.PostsAdapter
import com.vlabs.jsonplaceholder.ui.userdetails.UserDetailsActivity
import com.vlabs.jsonplaceholer.presentation.model.PostView
import com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.post.PostsContract
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_posts_list.view.*
import javax.inject.Inject

class PostFragment : Fragment(), PostsContract.View {


    override fun showPosts(posts: List<PostView>) {
        postsAdapter.posts = posts.map { postMapper.mapToViewModel(it) }
        postsAdapter.notifyDataSetChanged()
        //recycler_posts.visibility = View.VISIBLE
    }

    override fun hidePosts() {
        //recycler_posts.visibility = View.VISIBLE
    }

    override fun showErrorState() {

    }

    override fun hideErrorState() {

    }

    override fun showEmptyState() {

    }

    override fun hideEmptyState() {

    }

    override fun setPresenter(presenter: PostsContract.Presenter) {
        onboardingPresenter = presenter
    }

    override fun onStart() {
        super.onStart()
        onboardingPresenter.start(userId)
    }

    @Inject lateinit var onboardingPresenter: PostsContract.Presenter
    @Inject lateinit var postsAdapter: PostsAdapter
    @Inject lateinit var postMapper: PostMapper
    lateinit var posts: List<PostView>
    var userId: Int = -1

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_posts_list, container, false)
        //rootView.section_label.text = "Post fragment"
        rootView.recycler_posts.layoutManager = LinearLayoutManager(activity)
        userId = arguments.getInt(ARG_SECTION_NUMBER, -1)

        //postMapper = PostMapper()
        //postsAdapter = PostsAdapter(context)
        rootView.recycler_posts.adapter = postsAdapter

        //postsAdapter.posts = posts.map { postMapper.mapToViewModel(it) }
        //postsAdapter.notifyDataSetChanged()
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
        fun newInstance(posts: List<PostView>): PostFragment {
            val fragment = PostFragment()
            val args = Bundle()
            fragment.arguments = args
            fragment.posts = posts
            return fragment
        }

        fun newInstance(sectionNumber: Int): PostFragment {
            val fragment = PostFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            //fragment.albums = albums
            return fragment
        }
    }
}