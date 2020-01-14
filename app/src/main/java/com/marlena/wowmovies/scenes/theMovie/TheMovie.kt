package com.marlena.wowmovies.scenes.theMovie

import com.marlena.wowmovies.model.domain.ThePicture
import com.marlena.wowmovies.model.entity.InfoEntity

interface TheMovie {
    interface View {
        fun showMessage(message: String)
        fun getEdt(): String
        fun onBackPressed()
    }

    interface Presenter {
        fun insertMyPicture(thePicture: ThePicture, sensations: String)
        fun deletePicture(url: String)
        fun getMyPictureByUrl(url: String): InfoEntity?
        fun getSensations(url: String): String
    }
}