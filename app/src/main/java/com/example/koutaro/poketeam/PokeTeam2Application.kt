package com.example.koutaro.poketeam

import android.app.Application
import io.realm.Realm

class PokeTeam2Application : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}