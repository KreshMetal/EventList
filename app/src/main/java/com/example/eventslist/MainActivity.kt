package com.example.eventslist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.eventslist.DI.DaggerAppComponent
import com.example.eventslist.MainScreen.MainScreenViewModel
import com.example.eventslist.MainScreen.mainScreen
import com.example.eventslist.Model.Session
import com.example.eventslist.ui.theme.EventsListTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    var sessions = emptyList<Session>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = DaggerAppComponent.create().getRepository()
        val response = CoroutineScope(Dispatchers.IO).launch {
            sessions = repo.getSessions()
        }
        setContent {
            EventsListTheme {
                mainScreen(MainScreenViewModel())
            }
        }
    }
}
