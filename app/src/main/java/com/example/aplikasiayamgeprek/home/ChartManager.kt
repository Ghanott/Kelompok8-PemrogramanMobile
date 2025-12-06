package com.example.aplikasiayamgeprek.home

object ChartManager {

    private val chartList = mutableListOf<ChartModel>()

    fun addToChart(item: ChartModel) {
        val existing = chartList.find { it.name == item.name }
        if (existing != null) {
            existing.qty++
        } else {
            chartList.add(item)
        }
    }

    fun removeItem(item: ChartModel) {
        chartList.remove(item)
    }

    fun  increase(item: ChartModel) {
        item.qty++
    }

    fun decrease(item: ChartModel) {
        if (item.qty > 1) item.qty--
    }

    fun getCartItems() = chartList

    fun getTotalPrice(): Int = chartList.sumOf { it.price * it.qty }

    fun getTotalItems(): Int = chartList.sumOf { it.qty }
}