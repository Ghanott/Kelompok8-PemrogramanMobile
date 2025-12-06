package com.example.aplikasiayamgeprek.home

import java.io.Serializable


data class MenuModel(
    val name: String,
    val price: String,
    val category: String,
    val image: Int,
    val description: String
) : Serializable