package com.marlena.wowmovies.scenes.theMovie

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.marlena.wowmovies.R
import com.squareup.picasso.Picasso
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.marlena.wowmovies.model.domain.Movie
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat.setTransitionName
import kotlinx.android.synthetic.main.activity_themovie.*

class TheMovieActivity : AppCompatActivity(), TheMovie.View {

    private lateinit var presenter: TheMoviePresenter
    private lateinit var Movie: Movie
    lateinit var description: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_themovie)

        presenter = TheMoviePresenter(this)

        val poster_path = intent.getStringExtra("imageUrl") ?: ""
        val title = intent.getStringExtra("imageName") ?: ""
        val overview = intent.getStringExtra("imageOverview") ?: ""
        description = presenter.getDescription(poster_path)

        setTransitionName(movieIMG, TRANSITION_IMAGE)

        if (poster_path.isEmpty()) {
            movieIMG.setImageResource(R.drawable.alerta_790x400)
        } else {
            setMovie(poster_path)
            setView(poster_path, title, overview)
            initListener(poster_path, title)
        }
    }

    private fun setMovie(poster_path: String) {
        Picasso.get().load(poster_path).into(movieIMG)
    }

    private fun setView(poster_path: String, title: String, overview: String) {
        poster_pathTXT.text = poster_path
        titleTXT.text = title

        if (overview.isNotEmpty()) {
            overviewTXT.text = overview
            overviewTXT.visibility = View.VISIBLE
            textView_overview.visibility = View.VISIBLE
        }
        if (description.isNotEmpty()) {
            my_movieCBX.isChecked = true
            setVisibility(true)
            descriptionTXT.text = description
        }
    }

    private fun initListener(poster_path: String, title: String) {

        my_movieCBX.setOnClickListener {
            if (my_movieCBX.isChecked) {
                setVisibility(true)
            } else {
                showAlertDialog(
                    poster_path,
                    "Atenção!",
                    "Você deseja remover essa imagem de Watched Movies?"
                )
            }
        }
        saveBTN.setOnClickListener {
            description = getEdt()

            if (description != "") {
                descriptionTXT.text = description
                commentsEDT.hint = ""
                Movie = Movie(title, poster_path)
                presenter.insertMyMovie(Movie, description)
                onBackPressed()
            }
        }
    }

    private fun showAlertDialog(poster_path: String, title: String, message: String) {
        AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton("APAGAR") { _: DialogInterface, _: Int ->
                run {
                    presenter.deleteMovie(poster_path)
                    description = ""
                    setVisibility(false)
                }
            }
            setNegativeButton("Cancelar") { _: DialogInterface, _: Int ->
                run {
                    my_movieCBX.setChecked(true)
                    showMessage("Cancelado")
                }
            }
            show()
        }
    }

    private fun setVisibility(check: Boolean) {
        if (check)
            View.VISIBLE.let {
                my_movieCBX.isChecked = check
                textView_description.visibility = it
                descriptionTXT.visibility = it
                commentsEDT.visibility = it
                saveBTN.visibility = it
            }
        else {
            if (description == "") {
                textView_description.visibility = View.GONE
                descriptionTXT.visibility = View.GONE
            } else {
                textView_description.visibility = View.VISIBLE
                descriptionTXT.visibility = View.VISIBLE
            }
            my_movieCBX.isChecked = check
            commentsEDT.visibility = View.GONE
            saveBTN.visibility = View.GONE
        }
    }

    override fun getEdt(): String {
        val description = commentsEDT.text.toString()
        if (description.isEmpty()) showMessage("Texto vazio!")
        return description
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    companion object {
        const val TRANSITION_IMAGE = "image"
    }

    fun showAlertDialog(view: View) {}
}
