package com.example.myapplication


data class CardInfo(
    var title: String,
    var priority: String,
    var Rdate: String,
    var Rtime: String,
    var isCompleted: Boolean = false // New property to track completion
)


