package com.example.eventslist

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventslist.DI.DaggerAppComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableMap
import javax.inject.Inject

class MainScreenViewModel : ViewModel() {
    private val _state = MutableStateFlow(MainScreenState(emptyMap<String, List<Session>>(), FavoriteList()))
    val state = _state.asStateFlow()

    var sessionsRepository : SessionsRepository = DaggerAppComponent.create().getRepository()

    init {
        viewModelScope.launch {
            val list = sessionsRepository.getSessions()
            _state.value = MainScreenState(list.groupBy { it.date }, FavoriteList())
        }
    }

    fun onAddFavoriteClicked(session: Session)
    {
        if (session.isFavorite)
        {
            session.isFavorite = false
            val newFavorites = buildList<Session?> {
                _state.value.favorites.values.forEach {
                    if (it != session) add(it)
                    else add(null)
                }
            }
            _state.value = MainScreenState(_state.value.sessions, FavoriteList(newFavorites))
        }
        else
        {
            if(_state.value.favorites.haveFreePlace())
            {
                session.isFavorite = true
                var up = false
                val newFavorites = buildList<Session?> {
                    _state.value.favorites.values.forEach {
                        if (!up && it == null)
                        {
                            add(session)
                            up = true
                        }
                        else add(it)
                    }
                }
                _state.value = MainScreenState(_state.value.sessions, FavoriteList(newFavorites))
            }
            else
            {
                Log.d("TAG", "Нельзя")
            }
        }
    }
}