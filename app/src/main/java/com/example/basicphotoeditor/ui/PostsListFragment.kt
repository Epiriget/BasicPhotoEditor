package com.example.basicphotoeditor.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basicphotoeditor.R
import com.example.basicphotoeditor.data.room.PostEntity
import com.example.basicphotoeditor.domain.FilterTransformation
import com.example.basicphotoeditor.domain.Post
import com.example.basicphotoeditor.presenter.PostListPresenter
import com.example.basicphotoeditor.presenter.PostListPresenterContract
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 */
class PostsListFragment : Fragment(), PostListViewContract {
    // Todo: change to di
    private lateinit var presenter: PostListPresenterContract
    private var layout: View? = null
    private var textView: TextView? = null
    private var list: RecyclerView? = null

    private val adapter = PostAdapter()

    private var filter1: Button? = null
    private var filter2: Button? = null
    private var filter3: Button? = null

    companion object {
        fun getInstance(): PostsListFragment = PostsListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.fragment_posts_list, container, false)
        textView = layout?.findViewById(R.id.frag_textview)
        list = layout?.findViewById(R.id.list)

        filter1 = layout?.findViewById(R.id.filter1_button)
        filter2 = layout?.findViewById(R.id.filter2_button)
        filter3 = layout?.findViewById(R.id.filter3_button)

        val recyclerLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerLayoutManager.scrollToPosition(0)
        list?.layoutManager = recyclerLayoutManager


        presenter = PostListPresenter(requireActivity().application)
        presenter.attachView(this)
        presenter.supportStreamPosts()

        dispatchFilters()

        return layout
    }

    override fun showStreamPosts(posts: PagingData<Post>) {
        textView?.text = posts.toString()
        list?.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.submitData(posts)
        }
    }


    private fun dispatchFilters() {
        filter1?.setOnClickListener { updateFilter(FilterTransformation.Filter.GREY) }
        filter2?.setOnClickListener { updateFilter(FilterTransformation.Filter.SEPIA) }
        filter3?.setOnClickListener { updateFilter(FilterTransformation.Filter.SKETCH) }
    }

    private fun updateFilter(filter: FilterTransformation.Filter) {
        adapter.currentFilter = filter;
        adapter.notifyDataSetChanged()
    }


    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
