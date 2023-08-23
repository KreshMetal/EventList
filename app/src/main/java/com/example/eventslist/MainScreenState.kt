package com.example.eventslist

data class MainScreenState(val sessions: Map<String, List<Session>>, val favorites: FavoriteList)
