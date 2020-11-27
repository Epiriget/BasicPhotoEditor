package com.example.basicphotoeditor.service

import io.reactivex.Flowable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface LentaService {
    @GET("articles")
    fun getPosts(): Single<LentaRss>

    companion object {
        private const val BASE_LENTA_URL = "https://lenta.ru/rss/"

        fun create(): LentaService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_LENTA_URL)
                .client(client)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(LentaService::class.java)
        }
    }
}

@Root(name = "rss", strict = false)
data class LentaRss(
    @param:Element(name="channel")
    @field:Element(name="channel")
    var channel: LentaChannel
)

@Root(name = "channel", strict = false)
data class LentaChannel(
    @param:Element(name="title")
    @field:Element(name="title")
    var title: String,
    @param:Element(name="image")
    @field:Element(name="image")
    var image: LentaImage,
    @param:ElementList(name="item", inline = true)
    @field:ElementList(name="item", inline = true)
    var items: List<LentaItem>
)

@Root(name = "item", strict = false)
data class LentaItem (
    @param:Element(name="guid")
    @field:Element(name="guid")
    var giud: String,
    @param:Element(name="title")
    @field:Element(name="title")
    var title: String,
    @param:Element(name="enclosure")
    @field:Element(name="enclosure")
    var enclosure: LentaEnclosure
)

@Root(name = "enclosure", strict = false)
class LentaEnclosure(
    @param:Attribute(name="url")
    @field:Attribute(name="url")
    var url: String
)

@Root(name = "image", strict = false)
data class LentaImage (
    @param:Element(name="url")
    @field:Element(name="url")
    var url: String,
    @param:Element(name="title")
    @field:Element(name="title")
    var title: String
)

