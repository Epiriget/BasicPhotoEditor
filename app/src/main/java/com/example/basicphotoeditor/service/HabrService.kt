package com.example.basicphotoeditor.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

const val HABR_BASE_URL = "https://habr.com/ru/rss/hubs/"

fun getRetrofit(): HabrApi {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .retryOnConnectionFailure(true)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(HABR_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    return retrofit.create(HabrApi::class.java)
}

@Root(name = "rss", strict = false)
data class HabrRss(
    @param:Element(name="channel")
    @field:Element(name="channel")
    var channel: HabrChannel
)

@Root(name = "channel", strict = false)
data class HabrChannel(
    @param:Element(name="title")
    @field:Element(name="title")
    var title: String,
    @param:Element(name="image")
    @field:Element(name="image")
    var image: HabrImage,
    @param:ElementList(name="item", inline = true)
    @field:ElementList(name="item", inline = true)
    var items: List<HabrItem>
)

@Root(name = "item", strict = false)
data class HabrItem (
    @param:Element(name="title")
    @field:Element(name="title")
    var title: String,
    @param:Element(name="description")
    @field:Element(name="description")
    var description: String
)

@Root(name = "image", strict = false)
data class HabrImage (
    @param:Element(name="url")
    @field:Element(name="url")
    var url: String,
    @param:Element(name="title")
    @field:Element(name="title")
    var title: String
)
