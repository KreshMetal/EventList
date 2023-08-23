package com.example.eventslist

data class FavoriteList(val values : List<Session?> = listOf<Session?>(null, null, null))
{
    fun isEmpty():Boolean
    {
        return values.all { it == null }
    }

    fun haveFreePlace():Boolean
    {
        return values.any { it == null }
    }
}