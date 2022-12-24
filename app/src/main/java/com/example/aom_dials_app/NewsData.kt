package com.example.aom_dials_app

data class News(
    val title : String,
    val description : String
)

data class MyData(
    val totalResults:Int,
    val articles:List<News>
)