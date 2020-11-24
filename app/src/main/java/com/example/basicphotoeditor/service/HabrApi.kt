package com.example.basicphotoeditor.service

import io.reactivex.Single
import retrofit2.http.GET

interface HabrApi {
    @GET("all/")
    fun getChannel(): Single<HabrRss>
}
