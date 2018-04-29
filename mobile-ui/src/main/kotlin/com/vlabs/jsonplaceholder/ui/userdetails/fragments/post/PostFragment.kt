package com.vlabs.jsonplaceholder.ui.userdetails.fragments.post

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

    override fun setPresenter(presenter: PostsContract.Presenter) {
        onboardingPresenter = presenter
    }

    override fun onStart() {
        super.onStart()
        //onboardingPresenter.start()
    }

    @Inject lateinit var onboardingPresenter: PostsContract.Presenter

    @Inject lateinit var postsAdapter: PostsAdapter
    @Inject lateinit var postMapper: PostMapper
    lateinit var posts: List<PostView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_posts_list, container, false)
        //rootView.section_label.text = "Post fragment"
        rootView.recycler_posts.layoutManager = LinearLayoutManager(activity)

        postMapper = PostMapper()
        postsAdapter = PostsAdapter(context)
        rootView.recycler_posts.adapter = postsAdapter

        postsAdapter.posts = posts.map { postMapper.mapToViewModel(it) }
        postsAdapter.notifyDataSetChanged()
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
    }
}