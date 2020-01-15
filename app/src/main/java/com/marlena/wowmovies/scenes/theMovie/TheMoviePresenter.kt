package com.marlena.wowmovies.scenes.theMovie

import com.marlena.wowmovies.model.domain.Movie
import com.marlena.wowmovies.model.entity.InfoEntity
import com.marlena.wowmovies.persistence.MyMovieDB

class TheMoviePresenter(private val view: TheMovie.View) : TheMovie.Presenter {

    override fun insertMyMovie(movie: Movie, description: String) {
        var info: InfoEntity?
        val poster_path = movie.poster_path

        if (poster_path.isNotEmpty()) {
            info = getMyMovieByUrl(poster_path)

            when {
                info == null -> {
                    info = convertDomainInToMyMovies(movie)
                    info.description = description
                    MyMovieDB.instance.mymoviesDAO().insert(info)
                    view.showMessage("Imagem adicionada com SUCESSO!")
                    view.onBackPressed()
                }
                description != info.description -> {
                    MyMovieDB.instance.mymoviesDAO().delete(info)
                    info.description = description
                    MyMovieDB.instance.mymoviesDAO().insert(info)
                    view.showMessage("Sensations foi editada com SUCESSO.")
                }
                else -> view.showMessage("Atenção! Imagem já Existe")
            }
        }
    }

    override fun getMyMovieByUrl(poster_path: String): InfoEntity? {
        return MyMovieDB.instance.mymoviesDAO().getByPosterPath(poster_path)
    }

    override fun getDescription(poster_path: String): String {
        val movie: InfoEntity? = getMyMovieByUrl(poster_path)
        return when {
            movie != null -> movie.description
            else -> ""
        }
    }

    private fun convertDomainInToMyMovies(movie: Movie): InfoEntity {
        val myMovie = InfoEntity()

        myMovie.poster_path = movie.poster_path
        myMovie.title = movie.title
        myMovie.favorite = true
        myMovie.description = ""
        return myMovie
    }

    override fun deleteMovie(poster_path: String) {
        val myPicture = getMyMovieByUrl(poster_path)
        if (myPicture != null) {
            MyMovieDB.instance.mymoviesDAO().delete(myPicture)
            view.showMessage("Imagem retirada de My Gallery")
        } else
            view.showMessage("Imagem retirada de My Gallery")
    }
}