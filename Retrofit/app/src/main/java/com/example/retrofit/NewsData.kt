package com.example.retrofit

data class NewsData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)