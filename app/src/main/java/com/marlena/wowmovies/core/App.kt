package com.marlena.wowmovies.core

import android.app.Application
import com.marlena.wowmovies.persistence.MyPicturesDB
import com.marlena.wowmovies.repository.MovieRepository
import com.marlena.wowmovies.service.MovieClient

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        MyPicturesDB.initialize(this)
        MovieClient.initialize()
    }

    companion object {
        val movieRepository = MovieRepository()
    }
}