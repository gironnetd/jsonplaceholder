package com.vlabs.jsonplaceholder.ui.userdetails.fragments.todo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vlabs.jsonplaceholder.R
import com.vlabs.jsonplaceholder.mappers.TodoMapper
import com.vlabs.jsonplaceholder.ui.userdetails.TodosAdapter
import com.vlabs.jsonplaceholer.presentation.model.TodoView
import com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.todo.TodosContract
import kotlinx.android.synthetic.main.fragment_todos_list.view.*
import javax.inject.Inject

class TodoFragment : Fragment(), TodosContract.View {



    override fun showTodos(todos: List<TodoView>) {
        todosAdapter.todos = todos.map { todoMapper.mapToViewModel(it) }
        todosAdapter.notifyDataSetChanged()
        //recycler_todos.visibility = View.VISIBLE
        }

    override fun hideTodos() {
        //recycler_todos.visibility = View.VISIBLE
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

    override fun setPresenter(presenter: TodosContract.Presenter) {
        onboardingPresenter = presenter
    }

    override fun onStart() {
        super.onStart()
        //onboardingPresenter.start()
    }

    @Inject lateinit var onboardingPresenter: TodosContract.Presenter
    @Inject lateinit var todosAdapter: TodosAdapter
    @Inject lateinit var todoMapper: TodoMapper
    lateinit var todos: List<TodoView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_todos_list, container, false)
        //rootView.section_label.text = "Todo fragment"
        rootView.recycler_todos.layoutManager = LinearLayoutManager(activity)
        todoMapper = TodoMapper()
        todosAdapter = TodosAdapter(context)
        rootView.recycler_todos.adapter = todosAdapter

        todosAdapter.todos = todos.map { todoMapper.mapToViewModel(it) }
        todosAdapter.notifyDataSetChanged()
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
        fun newInstance(todos: List<TodoView>): TodoFragment {
            val fragment = TodoFragment()
            val args = Bundle()
            //args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            fragment.todos = todos
            return fragment
        }
    }
}