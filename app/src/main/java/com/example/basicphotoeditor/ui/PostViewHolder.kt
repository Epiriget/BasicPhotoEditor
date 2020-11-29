package com.example.basicphotoeditor.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.basicphotoeditor.R
import com.example.basicphotoeditor.domain.FilterTransformation
import com.example.basicphotoeditor.domain.Post
import com.squareup.picasso.Picasso

class PostViewHolder(view: View): RecyclerView.ViewHolder(view) {
//    private val title = view.findViewById<TextView>(R.id.post_title)
//    private val imageUrl = view.findViewById<TextView>(R.id.post_image_url)
    private val image = view.findViewById<ImageView>(R.id.post_image)
//    private val source = view.findViewById<TextView>(R.id.post_source)



    fun bind(post: Post?) {
        post?.let {
            Picasso.get()
                .load(it.imageUrl)
                .resize(200, 200)
                .placeholder(R.drawable.ic_photo_placeholder)
                .centerCrop()
                .transform(FilterTransformation(it.filter))
                .into(image)
        }
    }

    companion object {
        fun create(parent: ViewGroup): PostViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.post_view_item, parent, false)
            return PostViewHolder(view)
        }
    }

}
