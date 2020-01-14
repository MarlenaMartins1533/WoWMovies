package com.marlena.wowmovies.scenes.theMovie

import com.marlena.wowmovies.model.domain.ThePicture
import com.marlena.wowmovies.model.entity.InfoEntity
import com.marlena.wowmovies.persistence.MyPicturesDB

class TheMoviePresenter(private val view: TheMovie.View) : TheMovie.Presenter {

    override fun insertMyPicture(thePicture: ThePicture, sensations: String) {
        var info: InfoEntity?
        val url = thePicture.url

        if (url.isNotEmpty()) {
            info = getMyPictureByUrl(url)

            when {
                info == null -> {
                    info = convertDomainInToMyPictures(thePicture)
                    info.sensations = sensations
                    MyPicturesDB.instance.mypicturesDAO().insert(info)
                    view.showMessage("Imagem adicionada com SUCESSO!")
                    view.onBackPressed()
                }
                sensations != info.sensations -> {
                    MyPicturesDB.instance.mypicturesDAO().delete(info)
                    info.sensations = sensations
                    MyPicturesDB.instance.mypicturesDAO().insert(info)
                    view.showMessage("Sensations foi editada com SUCESSO.")
                }
                else -> view.showMessage("Atenção! Imagem já Existe")
            }
        }
    }

    override fun getMyPictureByUrl(url: String): InfoEntity? {
        return MyPicturesDB.instance.mypicturesDAO().getByUrl(url)
    }

    override fun getSensations(url: String): String {
        val picture: InfoEntity? = getMyPictureByUrl(url)
        return when {
            picture != null -> picture.sensations
            else -> ""
        }
    }

    private fun convertDomainInToMyPictures(thePicture: ThePicture): InfoEntity {
        val myPicture = InfoEntity()

        myPicture.url = thePicture.url
        myPicture.name = thePicture.name
        myPicture.favorite = true
        myPicture.sensations = ""
        return myPicture
    }

    override fun deletePicture(url: String) {
        val myPicture = getMyPictureByUrl(url)
        if (myPicture != null) {
            MyPicturesDB.instance.mypicturesDAO().delete(myPicture)
            view.showMessage("Imagem retirada de My Gallery")
        } else
            view.showMessage("Imagem retirada de My Gallery")
    }
}