package com.marlena.wowmovies.scenes.watchedMovies

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
import com.marlena.wowmovies.model.domain.Movie
import com.marlena.wowmovies.scenes.adapters.infoadapter.InfoAdapter
import com.marlena.wowmovies.scenes.theMovie.TheMovieActivity
import kotlinx.android.synthetic.main.fragment_watched.*

class WatchedMoviesFragment : Fragment(),
    WatchedMovies.View, InfoAdapter.Listener {

    private lateinit var presenter: WatchedMovies.Presenter
    private val movieList = mutableListOf<Movie>()
    private var adapter: InfoAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = WatchedMoviesPresenter(this)
        return inflater.inflate(R.layout.fragment_watched, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapters()
        setupViews()
        makeRequests()
    }

    private fun setupAdapters() {
        adapter =
            InfoAdapter(
                movieList,
                this
            )
    }

    private fun setupViews() {
        recyclerViewRV?.adapter = adapter
    }

    private fun makeRequests() {
        presenter.getAllList()
    }

    override fun setAllList(list: List<Movie>) {
        movieList.clear()
        movieList.addAll(list)

        if (movieList.isEmpty()) displayFailure()
        else adapter?.notifyDataSetChanged()
    }

    private fun displayFailure() {
        when (1) {
            1 -> Toast.makeText(context, getString(R.string.erro1), Toast.LENGTH_LONG).show()
            2 -> Toast.makeText(context,getString(R.string.erro2), Toast.LENGTH_LONG).show()
            else -> Toast.makeText(context,getString(R.string.erro0), Toast.LENGTH_LONG).show()
        }
    }

    override fun openMovieFragment(movie: Movie, itemView: View) {

        val options = ActivityOptions.makeSceneTransitionAnimation(
            activity, Pair(itemView, TheMovieActivity.TRANSITION_IMAGE)
        )

        val intent = Intent(context, TheMovieActivity::class.java).apply {
            putExtra("imageTitle", movie.title)
            putExtra("imagePosterPath", (Constants.imageUrlMovie + movie.poster_path))
            putExtra("imageBackdropPath", (Constants.imageUrlMovie + movie.backdrop_path))
            putExtra("imageOverview", "")
        }
        activity?.startActivity(intent, options.toBundle())
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroy() {
        presenter.kill()
        super.onDestroy()
    }
}