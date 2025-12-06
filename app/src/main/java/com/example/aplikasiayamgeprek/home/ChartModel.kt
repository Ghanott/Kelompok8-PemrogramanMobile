package com.example.aplikasiayamgeprek.home

data class ChartModel (
    val name: String,
    val desc: String,
    val price: Int,
    val image: Int,
    var qty: Int = 1
)