package com.marlena.wowmovies.scenes.adapters.movieadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marlena.wowmovies.R
import com.marlena.wowmovies.data.Constants
import com.marlena.wowmovies.model.response.TheMovie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_picture.view.*

class MovieAdapter(
    private val theMovieList: List<TheMovie>,
    private val listener: Listener
) : RecyclerView.Adapter<MovieAdapter.PictureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_picture, parent, false)
        return PictureViewHolder(view)
    }

    override fun getItemCount() = theMovieList.size

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bindView(position)
    }

    inner class PictureViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(position: Int) {
            itemView.image_titleTXT?.text = theMovieList[position].title

            Picasso.get()
                .load(Constants.imageUrlMovie + theMovieList[position].poster_path)
                .error(R.drawable.alerta_790x400)
                .into(itemView.imageIMG)
            itemView.itemCV.isClickable = true

            itemView.itemCV.setOnClickListener {
                listener.openPictureFragment(
                    theMovieList[position],
                    itemView
                )
            }
        }
    }

    interface Listener {
        fun openPictureFragment(movie: TheMovie, itemView: View)
    }
}