package com.example.basicphotoeditor.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.basicphotoeditor.R
import com.example.basicphotoeditor.service.HabrApi
import com.example.basicphotoeditor.service.getRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchHabrList(getRetrofit())

    }

    fun fetchHabrList(api: HabrApi) {
        disposables.add(
            api.getChannel()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d("Retrofit", it.channel.items.size.toString())
                    },
                    {
                        Log.d("Retrofit", it.message.toString())
                    })
        )
    }
}
