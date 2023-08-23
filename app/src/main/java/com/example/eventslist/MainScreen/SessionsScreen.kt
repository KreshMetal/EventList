package com.example.eventslist.MainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.eventslist.Model.FavoriteList
import com.example.eventslist.R
import com.example.eventslist.Model.Session

@Composable
fun mainScreen(model : MainScreenViewModel)
{
    val state : MainScreenState by model.state.collectAsState()

    Column(Modifier.verticalScroll(rememberScrollState())) {
        favoriteSelection(favorites = state.favorites)
        Spacer(modifier = Modifier.padding(15.dp))
        sessionList(sessions = state.sessions) { model.onAddFavoriteClicked(it) }
    }
}

@Composable
fun sessionList(sessions: Map<String, List<Session>>, onFavoriteClicked: (Session) -> Unit)
{
//    LazyColumn(){
//        items(sessions.keys.toList()) { key ->
//            sessionsOnDay(date = key, sessions = sessions[key]!!)
//        }
//    }
    Text(
        text = "Сессии",
        color = MaterialTheme.colorScheme.secondary,
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.padding(start = 15.dp, bottom = 10.dp)
    )
    Column {
        for (item in sessions)
        {
            sessionsOnDay(date = item.key, sessions = item.value, onFavoriteClicked)
        }
    }
}

@Composable
fun sessionsOnDay(date: String, sessions: List<Session>, onFavoriteClicked: (Session) -> Unit)
{
    Column (
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(bottom = 10.dp, start = 15.dp, end = 15.dp)
    ) {
        Text(
            text = date,
            color = MaterialTheme.colorScheme.tertiary,
            style = MaterialTheme.typography.bodyMedium,
        )
        repeat(sessions.size)
        {
            ListCard(session = sessions[it], onFavoriteClicked)
        }
    }
//    LazyColumn (
//        contentPadding = PaddingValues(horizontal = 15.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        modifier = Modifier.padding(bottom = 10.dp)
//    ){
//        item { Text(
//            text = date,
//            color = MaterialTheme.colorScheme.tertiary,
//            style = MaterialTheme.typography.bodyMedium,
//        ) }
//        items(sessions.size){index ->
//            ListCard(session = sessions[index])
//        }
//    }
}

@Composable
fun favoriteSelection(favorites: FavoriteList)
{
    if (favorites.isEmpty()) return
    Text(
        text = "Избранное",
        color = MaterialTheme.colorScheme.secondary,
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.padding(start = 15.dp, top = 10.dp)
    )
    Row(Modifier.horizontalScroll(rememberScrollState()))
    {
        favorites.values.onEach { favoriteCard(it) }
    }
}

@Composable
fun favoriteCard(session: Session?)
{
    if (session == null) return
    Surface(modifier = Modifier
        .padding(15.dp)
        .width(150.dp)
        .shadow(elevation = 5.dp, shape = RoundedCornerShape(8.dp))
        .background(Color(0xFFFFFFFF))) {
        Column(){
            Text(
                text = session.timeInterval,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(start = 5.dp, top = 5.dp, end = 5.dp)
            )
            Text(
                text = session.date,
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 5.dp, end = 5.dp)
            )
            Text(
                text = session.speaker,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.headlineMedium,
                maxLines = 1,
                modifier = Modifier.padding(start = 5.dp, top = 5.dp, end = 5.dp),
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = session.description,
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 3,
                modifier = Modifier.padding(start = 5.dp, bottom = 5.dp, end = 5.dp),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ListCard(session: Session, onFavoriteClicked: (Session) -> Unit)
{
    Surface(
        modifier = Modifier
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(8.dp))
            .background(Color(0xFFFFFFFF))
            .height(100.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(model = session.imageUrl,
                contentDescription = session.description,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(100.dp)
                    .padding(horizontal = 5.dp, vertical = 5.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .weight(3f)
                    .padding(horizontal = 5.dp)
            ) {
                Text(
                    text = session.speaker,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.headlineMedium,
                )
                Text(
                    text = session.timeInterval,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.headlineMedium,
                )
                Text(
                    text = session.description,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            val drawableId = if(session.isFavorite) R.drawable.favorite else R.drawable.favorite_no
            Image(imageVector = ImageVector.vectorResource(
                id = drawableId),
                contentDescription = null,
                modifier = Modifier
                    .weight(0.5f)
                    .clickable { onFavoriteClicked(session) }
            )
        }
    }
}