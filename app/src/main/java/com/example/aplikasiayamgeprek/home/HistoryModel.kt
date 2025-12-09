package com.example.aplikasiayamgeprek.home

import java.io.Serializable


data class HistoryModel(
    val title: String,
    val subtitle: String,
    val unitPrice: String,
    val totalPrice: String,
    val status: String,
    val image: Int
) : Serializable
