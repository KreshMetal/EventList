package com.example.eventslist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.eventslist.DI.DaggerAppComponent
import com.example.eventslist.MainScreen.ListCard
import com.example.eventslist.MainScreen.favoriteCard
import com.example.eventslist.MainScreen.favoriteSelection
import com.example.eventslist.MainScreen.mainScreen
import com.example.eventslist.MainScreen.sessionList
import com.example.eventslist.ui.theme.EventsListTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
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
