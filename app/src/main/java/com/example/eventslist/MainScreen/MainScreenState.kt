package com.example.eventslist.MainScreen

import com.example.eventslist.Model.FavoriteList
import com.example.eventslist.Model.Session

data class MainScreenState(val sessions: Map<String, List<Session>>, val favorites: FavoriteList)
