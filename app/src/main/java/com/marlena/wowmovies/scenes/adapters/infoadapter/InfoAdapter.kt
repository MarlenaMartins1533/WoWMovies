package com.marlena.wowmovies.scenes.adapters.infoadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marlena.wowmovies.R
import com.marlena.wowmovies.model.domain.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class InfoAdapter(
    private val movieList: List<Movie>,
    private val listener: Listener
) : RecyclerView.Adapter<InfoAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindView(position)
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(position: Int) {
            itemView.image_titleTXT?.text = movieList[position].title

            Picasso.get()
                .load(movieList[position].poster_path)
                .error(R.drawable.alerta_790x400)
                .into(itemView.imageIMG)
            itemView.itemCV.isClickable = true

            itemView.itemCV.setOnClickListener {
                listener.openMovieFragment(
                    movieList[position],
                    itemView
                )
            }
        }
    }

    interface Listener {
        fun openMovieFragment(movie: Movie, itemView: View)
    }
}