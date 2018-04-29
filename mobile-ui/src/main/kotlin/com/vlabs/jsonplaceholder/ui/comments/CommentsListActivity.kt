package com.vlabs.jsonplaceholder.ui.comments

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_users_list.*
import com.vlabs.jsonplaceholder.presentation.model.UserView
import com.vlabs.jsonplaceholder.R
import com.vlabs.jsonplaceholder.mappers.CommentMapper
import com.vlabs.jsonplaceholder.mappers.UserMapper
import com.vlabs.jsonplaceholder.presentation.ui.posts.UsersListContract
import com.vlabs.jsonplaceholer.presentation.model.CommentView
import com.vlabs.jsonplaceholer.presentation.model.POST_ID_KEY
import com.vlabs.jsonplaceholer.presentation.ui.comments.CommentsContract
import kotlinx.android.synthetic.main.activity_comments.*
import javax.inject.Inject

class CommentsListActivity: AppCompatActivity(), CommentsContract.View {

    @Inject lateinit var onboardingPresenter: CommentsContract.Presenter
    @Inject lateinit var commentsListAdapter: CommentsListAdapter
    @Inject lateinit var mapper: CommentMapper
    var postId: Int = -1

    override fun setPresenter(presenter: CommentsContract.Presenter) {
        onboardingPresenter = presenter
    }

    override fun hideProgress() {
        users_list_progress.visibility = View.VISIBLE
    }

    override fun showProgress() {
        users_list_progress.visibility = View.GONE
    }

    override fun showComments(users: List<CommentView>) {
        commentsListAdapter.comments = users.map { mapper.mapToViewModel(it) }
        commentsListAdapter.notifyDataSetChanged()
        recycler_comments.visibility = View.VISIBLE
    }

    override fun hideComments() {
        recycler_comments.visibility = View.VISIBLE
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
        setContentView(R.layout.activity_comments)
        AndroidInjection.inject(this)
        postId = intent.getIntExtra(POST_ID_KEY, -1)
        setupBrowseRecycler()
    }

    override fun onStart() {
        super.onStart()
        onboardingPresenter.start(postId)
    }

    private fun setupBrowseRecycler() {
        recycler_comments.layoutManager = LinearLayoutManager(this)
        recycler_comments.adapter = commentsListAdapter
    }
}