package com.example.myapplication

object DataObject {
    private val dataList = mutableListOf<CardInfo>()

    fun setData(title: String, priority: String, date: String, time: String) {
        val cardInfo = CardInfo(title, priority, date, time)
        dataList.add(cardInfo)
    }

    fun getAllData(): List<CardInfo> = dataList

    fun deleteAll() {
        dataList.clear()
    }
}
