package com.example.aplikasiayamgeprek.home

object HistoryManager {
    private val historyList = mutableListOf<HistoryModel>()

    fun addOrder(order: HistoryModel) {
        historyList.add(0, order)
    }

    fun getHistory(): List<HistoryModel> = historyList
}