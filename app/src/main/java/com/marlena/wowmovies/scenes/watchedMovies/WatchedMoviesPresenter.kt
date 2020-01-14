package com.marlena.wowmovies.scenes.watchedMovies

import com.marlena.wowmovies.model.domain.ThePicture
import com.marlena.wowmovies.model.entity.InfoEntity
import com.marlena.wowmovies.persistence.MyPicturesDB
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class WatchedMoviesPresenter(private val view: WatchedMovies.View) : WatchedMovies.Presenter, CoroutineScope {
    override val coroutineContext: CoroutineContext get() = Dispatchers.Main
    private var job: Job? = null

    override fun getAllList() {
        job = launch {
            val myPicturesList = ArrayList<InfoEntity>()

            withContext(Dispatchers.IO) {
                MyPicturesDB.instance.mypicturesDAO().getAllMyPictures().let {
                    myPicturesList.addAll(it)
                }
            }
            val thePictureList = convertMyPicturesListInToDomain(myPicturesList)
            view.setAllList(thePictureList)
        }
    }

    private fun convertMyPicturesListInToDomain(
        infoList: MutableList<InfoEntity>
    ): List<ThePicture> {

        return infoList.map {
            ThePicture(
                name = it.name,
                url = it.url
            )
        }
    }

    override fun kill() {
        job?.cancel()
    }
}