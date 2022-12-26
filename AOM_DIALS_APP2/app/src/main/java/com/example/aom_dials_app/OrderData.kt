package com.example.aom_dials_app

data class Orders(
    val title : String,
    val description : String,
    val urlToImage : String
)

data class MyData(
    val totalResults:Int,
    val articles:List<Orders>
)
