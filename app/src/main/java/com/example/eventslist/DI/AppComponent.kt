package com.example.eventslist.DI

import com.example.eventslist.SessionsRepository
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun getRepository(): SessionsRepository
}

@Module
object NetworkModule
{
    @Provides
    fun okHttpClient(): OkHttpClient
    {
        return OkHttpClient()
    }
}