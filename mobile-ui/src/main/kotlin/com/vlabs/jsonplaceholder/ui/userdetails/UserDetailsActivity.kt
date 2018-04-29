package com.vlabs.jsonplaceholder.ui.userdetails

import android.support.design.widget.TabLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.vlabs.jsonplaceholder.R
import com.vlabs.jsonplaceholder.mappers.AlbumMapper
import com.vlabs.jsonplaceholder.mappers.PostMapper
import com.vlabs.jsonplaceholder.mappers.TodoMapper
import com.vlabs.jsonplaceholder.presentation.model.USER_ID_KEY
import com.vlabs.jsonplaceholder.ui.userdetails.fragments.album.AlbumFragment
import com.vlabs.jsonplaceholder.ui.userdetails.fragments.post.PostFragment
import com.vlabs.jsonplaceholder.ui.userdetails.fragments.todo.TodoFragment
import com.vlabs.jsonplaceholer.presentation.model.AlbumView
import com.vlabs.jsonplaceholer.presentation.model.PostView
import com.vlabs.jsonplaceholer.presentation.model.TodoView
import com.vlabs.jsonplaceholer.presentation.ui.userdetails.UserDetailsContract
import dagger.android.AndroidInjection

import kotlinx.android.synthetic.main.activity_users_details.*
import kotlinx.android.synthetic.main.fragment_users_details.view.*
import javax.inject.Inject

class UserDetailsActivity : AppCompatActivity(), UserDetailsContract.View {


    @Inject lateinit var onboardingPresenter: UserDetailsContract.Presenter
    @Inject lateinit var albumsAdapter: AlbumsAdapter
    @Inject lateinit var albumMapper: AlbumMapper
    @Inject lateinit var postsAdapter: PostsAdapter
    @Inject lateinit var postMapper: PostMapper
    @Inject lateinit var todosAdapter: TodosAdapter
    @Inject lateinit var todoMapper: TodoMapper

    var userId: Int = -1
    var albums: List<AlbumView>? = null
    var posts: List<PostView>? = null
    var todos: List<TodoView>? = null

    override fun hideAlbums() {
        //recycler_albums.visibility = View.VISIBLE
    }

    override fun hidePosts() {
        //recycler_posts.visibility = View.VISIBLE
    }

    override fun hideTodos() {
        //recycler_todos.visibility = View.VISIBLE
    }

    override fun hideProgress() {
    }

    override fun showProgress() {
    }

    override fun showAlbums(albums: List<AlbumView>) {
        //albumsAdapter.albums = albums.map { albumMapper.mapToViewModel(it) }
        //albumsAdapter.notifyDataSetChanged()
        //recycler_albums.visibility = View.VISIBLE
        this.albums = albums
        isAllInit()
    }

    override fun showPosts(posts: List<PostView>) {
        //postsAdapter.posts = posts.map { postMapper.mapToViewModel(it) }
        //postsAdapter.notifyDataSetChanged()
        //recycler_posts.visibility = View.VISIBLE
        this.posts = posts
        isAllInit()
    }

    override fun showTodos(todos: List<TodoView>) {
        //todosAdapter.todos = todos.map { todoMapper.mapToViewModel(it) }
        //todosAdapter.notifyDataSetChanged()
        //recycler_todos.visibility = View.VISIBLE
        this.todos = todos
        isAllInit()
    }

    fun isAllInit() {
        if(albums != null && albums!!.isNotEmpty() && posts != null && posts!!.isNotEmpty() && todos != null && todos!!.isNotEmpty()) {
            mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
            container.adapter = mSectionsPagerAdapter
        }
    }

    override fun showErrorState() {
    }

    override fun hideErrorState() {
    }

    override fun showEmptyState() {
    }

    override fun hideEmptyState() {
    }

    override fun setPresenter(presenter: UserDetailsContract.Presenter) {
        onboardingPresenter = presenter
    }

    override fun onStart() {
        super.onStart()
        onboardingPresenter.start(userId)
    }

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_details)
        AndroidInjection.inject(this)
        userId = intent.getIntExtra(USER_ID_KEY, -1)
        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        //mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_users_details, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            when(position) {
                0 -> return AlbumFragment.newInstance(albums!!)
                1 -> return PostFragment.newInstance(posts!!)
                2 -> return TodoFragment.newInstance(todos!!)
                else -> return PlaceholderFragment.newInstance(position + 1)
            }
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_users_details, container, false)
            rootView.section_label.text = getString(R.string.section_format, arguments?.getInt(ARG_SECTION_NUMBER))
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
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }
}
