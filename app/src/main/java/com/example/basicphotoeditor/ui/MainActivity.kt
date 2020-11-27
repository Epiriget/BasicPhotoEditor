package com.example.basicphotoeditor.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.basicphotoeditor.R
import io.reactivex.disposables.CompositeDisposable


class MainActivity : AppCompatActivity() {
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, PostsListFragment.getInstance())
            .commit()
    }

}
