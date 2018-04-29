package com.vlabs.jsonplaceholder.ui.users

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_users_list.*
import com.vlabs.jsonplaceholder.presentation.model.UserView
import com.vlabs.jsonplaceholder.R
import com.vlabs.jsonplaceholder.mappers.UserMapper
import com.vlabs.jsonplaceholder.presentation.ui.posts.UsersListContract
import javax.inject.Inject

class UsersListActivity: AppCompatActivity(), UsersListContract.View {

    @Inject lateinit var onboardingPresenter: UsersListContract.Presenter
    @Inject lateinit var usersListAdapter: UsersListAdapter
    @Inject lateinit var mapper: UserMapper

    override fun setPresenter(presenter: UsersListContract.Presenter) {
        onboardingPresenter = presenter
    }

    override fun hideProgress() {
        users_list_progress.visibility = View.VISIBLE
    }

    override fun showProgress() {
        users_list_progress.visibility = View.GONE
    }

    override fun showUsers(users: List<UserView>) {
        usersListAdapter.users = users.map { mapper.mapToViewModel(it) }
        usersListAdapter.notifyDataSetChanged()
        recycler_users.visibility = View.VISIBLE
    }

    override fun hideUsers() {
        recycler_users.visibility = View.VISIBLE
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
        setContentView(R.layout.activity_users_list)
        AndroidInjection.inject(this)
        setupBrowseRecycler()
    }

    override fun onStart() {
        super.onStart()
        onboardingPresenter.start()
    }

    private fun setupBrowseRecycler() {
        recycler_users.layoutManager = LinearLayoutManager(this)
        recycler_users.adapter = usersListAdapter
    }
}