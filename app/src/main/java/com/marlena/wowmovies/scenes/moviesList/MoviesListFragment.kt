package com.marlena.wowmovies.scenes.moviesList

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.marlena.wowmovies.R
import com.marlena.wowmovies.data.Constants
import com.marlena.wowmovies.model.response.TheMovie
import com.marlena.wowmovies.scenes.adapters.movieadapter.MovieAdapter
import com.marlena.wowmovies.scenes.theMovie.TheMovieActivity
import kotlinx.android.synthetic.main.fragment_movie.*

class MoviesListFragment : Fragment(),
    MoviesList.View, MovieAdapter.Listener {

    private val movieList = mutableListOf<TheMovie>()
    private lateinit var presenter: MoviesListPresenter
    private var adapter: MovieAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = MoviesListPresenter(this)
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapters()
        setupViews()
        makeRequests()
    }

    private fun setupAdapters() {
        adapter =
            MovieAdapter(
                movieList,
                this
            )
    }

    private fun setupViews() {
        recyclerViewRV?.adapter = adapter
    }

    override fun makeRequests() {
        presenter.getMoviesList()
    }

    override fun setList(list: List<TheMovie>) {
        movieList.clear()
        movieList.addAll(list)

        adapter?.notifyDataSetChanged()
    }

    override fun displayFailure(error: Int) {
        Toast.makeText(context, "Erro na solicitação", Toast.LENGTH_LONG).show()
    }

    override fun openPictureFragment(movie: TheMovie, itemView: View) {

        val options = ActivityOptions.makeSceneTransitionAnimation(
            activity,
            Pair(itemView, TheMovieActivity.TRANSITION_IMAGE)
        )

        val intent = Intent(context, TheMovieActivity::class.java).apply {
            putExtra("imageName", movie.title)
            putExtra("imageUrl", (Constants.imageUrlMovie + movie.poster_path))
            putExtra("imageOverview", movie.overview)
        }
        activity?.startActivity(intent, options.toBundle())
        adapter?.notifyDataSetChanged()
    }
}