package com.example.basicphotoeditor.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.basicphotoeditor.R
import com.example.basicphotoeditor.data.room.PostEntity
import com.example.basicphotoeditor.domain.PostListPresenter
import com.example.basicphotoeditor.domain.PostListPresenterContract
import com.example.basicphotoeditor.domain.PresenterBase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

/**
 * A simple [Fragment] subclass.
 */
class PostsListFragment : Fragment(), PostListView {
    // Todo: change to di
    private lateinit var presenter: PostListPresenterContract
    private var layout: View? = null
    private  var textView: TextView? = null

    companion object {
        fun getInstance(): PostsListFragment = PostsListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.fragment_posts_list, container, false)
        textView = layout?.findViewById(R.id.frag_textview)
        presenter = PostListPresenter(requireActivity().application)
        presenter.attachView(this)
        return layout
    }

    override fun showPosts(posts: List<PostEntity>) {
        textView?.text = posts.size.toString()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
