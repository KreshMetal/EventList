package com.example.eventslist

data class Session(
    val date: String,
    val description: String,
    val id: String,
    val imageUrl: String,
    var isFavorite: Boolean,
    val speaker: String,
    val timeInterval: String
)
