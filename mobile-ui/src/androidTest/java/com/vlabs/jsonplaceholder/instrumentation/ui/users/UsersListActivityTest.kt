package com.vlabs.jsonplaceholder.instrumentation.ui.users

import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.nhaarman.mockito_kotlin.whenever
import com.vlabs.jsonplaceholder.R
import com.vlabs.jsonplaceholder.domain.models.User
import com.vlabs.jsonplaceholder.instrumentation.test.TestApplication
import com.vlabs.jsonplaceholder.instrumentation.test.factory.UserFactory
import com.vlabs.jsonplaceholder.instrumentation.test.util.RecyclerViewMatcher
import com.vlabs.jsonplaceholder.ui.users.UsersListActivity
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UsersListActivityTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<UsersListActivity>(UsersListActivity::class.java, false, false)

    @Test
    fun activityLaunches() {
        stubUserRepositoryGetUsers(Single.just(UserFactory.makeUserList(2)))
        activity.launchActivity(null)
    }

    @Test
    fun usersDisplay() {
        val users = UserFactory.makeUserList(1)
        stubUserRepositoryGetUsers(Single.just(users))
        activity.launchActivity(null)

        checkUserDetailsDisplay(users[0], 0)
    }

    @Test
    fun usersAreScrollable() {
        val users = UserFactory.makeUserList(10)
        stubUserRepositoryGetUsers(Single.just(users))
        activity.launchActivity(null)

        users.forEachIndexed { index, user ->
            Espresso.onView(ViewMatchers.withId(R.id.recycler_users)).perform(RecyclerViewActions.
                    scrollToPosition<RecyclerView.ViewHolder>(index))
            checkUserDetailsDisplay(user, index) }
    }

    private fun checkUserDetailsDisplay(user: User, position: Int) {
        Espresso.onView(RecyclerViewMatcher.withRecyclerView(R.id.recycler_users).atPosition(position))
                .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(user.name))))
        Espresso.onView(RecyclerViewMatcher.withRecyclerView(R.id.recycler_users).atPosition(position))
                .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(user.userName))))
    }

    private fun stubUserRepositoryGetUsers(single: Single<List<User>>) {
        whenever(TestApplication.appComponent().userRepository().getUsers())
                .thenReturn(single)
    }

}