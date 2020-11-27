package com.example.basicphotoeditor.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.basicphotoeditor.R
import com.example.basicphotoeditor.data.room.PostEntity
import kotlinx.android.synthetic.main.post_view_item.view.*

class PostViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val title = view.findViewById<TextView>(R.id.post_title)
    private val image = view.findViewById<TextView>(R.id.post_image)
    private val source = view.findViewById<TextView>(R.id.post_source)

    private var post: PostEntity? = null

    init {
        view.setOnClickListener {
            post?.id?.let {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                view.context.startActivity(intent)
            }
        }
    }

    fun bind(post: PostEntity?) {
        title.text = post?.title
        image.text = post?.image
        source.text = post?.source
    }

    companion object {
        fun create(parent: ViewGroup): PostViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.post_view_item, parent, false)
            return PostViewHolder(view)
        }
    }

}
