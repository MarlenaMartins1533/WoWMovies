package com.marlena.wowmovies.scenes.watchedMovies

import com.marlena.wowmovies.model.domain.Movie
import com.marlena.wowmovies.model.entity.InfoEntity
import com.marlena.wowmovies.persistence.MyMovieDB
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class WatchedMoviesPresenter(private val view: WatchedMovies.View) : WatchedMovies.Presenter, CoroutineScope {
    override val coroutineContext: CoroutineContext get() = Dispatchers.Main
    private var job: Job? = null

    override fun getAllList() {
        job = launch {
            val myMoviesList = ArrayList<InfoEntity>()

            withContext(Dispatchers.IO) {
                MyMovieDB.instance.mymoviesDAO().getAllMyMovies().let {
                    myMoviesList.addAll(it)
                }
            }
            val movieList = convertMyMoviesListInToDomain(myMoviesList)
            view.setAllList(movieList)
        }
    }

    private fun convertMyMoviesListInToDomain(
        infoList: MutableList<InfoEntity>
    ): List<Movie> {

        return infoList.map {
            Movie(
                title = it.title,
                poster_path = it.poster_path
            )
        }
    }

    override fun kill() {
        job?.cancel()
    }
}