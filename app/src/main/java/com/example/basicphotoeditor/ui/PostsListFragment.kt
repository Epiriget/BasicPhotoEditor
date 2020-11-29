package com.example.basicphotoeditor.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basicphotoeditor.R
import com.example.basicphotoeditor.domain.FilterTransformation
import com.example.basicphotoeditor.domain.Post
import com.example.basicphotoeditor.presenter.PostListPresenter
import com.example.basicphotoeditor.presenter.PostListPresenterContract
import kotlinx.android.synthetic.main.posts_load_state_footer_view_item.*
import kotlinx.coroutines.launch



class PostsListFragment : Fragment(), PostListViewContract {
    // Todo: change to di
    private lateinit var presenter: PostListPresenterContract
    private var layout: View? = null
    private var list: RecyclerView? = null

    private val adapter = PostAdapter()

    private var filter1: Button? = null
    private var filter2: Button? = null
    private var filter3: Button? = null
    private var filter4: Button? = null

    private var listRetryButton: Button? = null
    private var listProgressBar: ProgressBar? = null

    companion object {
        fun getInstance(): PostsListFragment = PostsListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.fragment_posts_list, container, false)
        list = layout?.findViewById(R.id.list)

        filter1 = layout?.findViewById(R.id.filter1_button)
        filter2 = layout?.findViewById(R.id.filter2_button)
        filter3 = layout?.findViewById(R.id.filter3_button)
        filter4 = layout?.findViewById(R.id.filter4_button)
        listRetryButton = layout?.findViewById(R.id.list_retry_button)
        listProgressBar = layout?.findViewById(R.id.list_progress_bar)
        listRetryButton?.setOnClickListener { adapter.retry() }

        initRecyclerView()

        presenter = PostListPresenter(requireActivity().application)
        presenter.attachView(this)
        presenter.supportStreamPosts()

        dispatchFilters()

        return layout
    }

    private fun initRecyclerView() {
        val recyclerLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerLayoutManager.scrollToPosition(0)
        list?.layoutManager = recyclerLayoutManager
        val decoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        list?.addItemDecoration(decoration)

        list?.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PostLoadStateAdapter { adapter.retry() },
            footer = PostLoadStateAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            list?.isVisible = loadState.source.refresh is LoadState.NotLoading
            listProgressBar?.isVisible = loadState.source.refresh is LoadState.Loading
            listRetryButton?.isVisible = loadState.source.refresh is LoadState.Error
        }
    }

    override fun showStreamPosts(posts: PagingData<Post>) {
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.submitData(posts)
        }
    }


    private fun dispatchFilters() {
        filter1?.setOnClickListener { updateFilter(FilterTransformation.Filter.GREY) }
        filter2?.setOnClickListener { updateFilter(FilterTransformation.Filter.SEPIA) }
        filter3?.setOnClickListener { updateFilter(FilterTransformation.Filter.SKETCH) }
        filter4?.setOnClickListener { updateFilter(FilterTransformation.Filter.NONE) }
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
