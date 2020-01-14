package com.marlena.wowmovies.scenes.adapters.infoadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marlena.wowmovies.R
import com.marlena.wowmovies.model.domain.ThePicture
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_picture.view.*

class InfoAdapter(
    private val pictureList: List<ThePicture>,
    private val listener: Listener
) : RecyclerView.Adapter<InfoAdapter.PictureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_picture, parent, false)
        return PictureViewHolder(view)
    }

    override fun getItemCount() = pictureList.size

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bindView(position)
    }

    inner class PictureViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(position: Int) {
            itemView.image_titleTXT?.text = pictureList[position].name

            Picasso.get()
                .load(pictureList[position].url)
                .error(R.drawable.alerta_790x400)
                .into(itemView.imageIMG)
            itemView.itemCV.isClickable = true

            itemView.itemCV.setOnClickListener {
                listener.openPictureFragment(
                    pictureList[position],
//                    pictureList[position].name,
//                    pictureList[position].url,
//                    "",
//                    pictureList[position].sensations,
                    itemView
                )
            }
        }
    }

    interface Listener {
        fun openPictureFragment(picture: ThePicture, itemView: View)
    }
}