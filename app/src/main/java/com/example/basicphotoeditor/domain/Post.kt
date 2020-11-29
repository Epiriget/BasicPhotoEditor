package com.example.basicphotoeditor.domain

data class Post(
    var id: String,
    var imageUrl: String,
    var filter: FilterTransformation.Filter,
    var title: String,
    var source: String
)