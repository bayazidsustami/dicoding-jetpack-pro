package com.dicoding.submission.jetpack.ui.mainUI.detail.movie

import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.dicoding.submission.jetpack.R
import com.dicoding.submission.jetpack.data.movie.DetailMovieEntity
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.databinding.ActivityDetailMovieBinding
import com.dicoding.submission.jetpack.ui.baseUI.BaseActivity
import com.dicoding.submission.jetpack.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : BaseActivity<ActivityDetailMovieBinding>(
        ActivityDetailMovieBinding::inflate
) {
    private val viewModel: DetailMovieViewModel by viewModel()

    private lateinit var moviesEntity: MoviesEntity

    private var menu: Menu? = null

    override fun initializeView(bind: ActivityDetailMovieBinding) {

        val intent = intent.getParcelableExtra<MoviesEntity>(EXTRA_DATA_MOVIE)

        intent?.id?.let { viewModel.setMovieSelected(it) }

        if (intent != null){
            moviesEntity = intent
            setFavorite(moviesEntity.isFavorite)
        }

        supportActionBar?.run {
            title = intent?.title
            setDisplayHomeAsUpEnabled(true)
        }

        viewModel.detailMovie.observe(this){ result ->
            when(result){
                is Result.Loading -> {
                    bind.progressBar.visible()
                }
                is Result.Error -> {
                    bind.progressBar.gone()
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
                is Result.Success -> {
                    bind.progressBar.gone()
                    bind.viewContent.visible()
                    showDetail(result.data, intent?.posterPath, intent?.releaseDate)
                }
            }
        }
    }

    private fun showDetail(
        dataDetail: DetailMovieEntity,
        urlImg: String?,
        releaseDate: String?){
        binding.run {
            viewPoster.run {
                Glide.with(this@DetailMovieActivity)
                    .load(urlImg)
                    .placeholder(this@DetailMovieActivity.circularProgress())
                    .into(imgPoster)
                tvTitle.text = dataDetail.title
            }
            viewDetail.run {
                tvTitle.text = dataDetail.title
                tvStatus.text = dataDetail.status
                tvDate.text = releaseDate
            }
            tvDescription.text = dataDetail.overview
            tvHomePage.text = dataDetail.homepage

            tvHomePage.setOnClickListener {
                Commons.openBrowser(this@DetailMovieActivity, dataDetail.homepage)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_is_favorite, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.is_favorite){
            val isFavorite = !moviesEntity.isFavorite
            setFavorite(isFavorite)
            viewModel.setFavorite(moviesEntity, isFavorite)
            true
        } else{
            super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite(state: Boolean){
        if(menu == null) return
        val menuItem = menu?.findItem(R.id.is_favorite)
        menuItem?.isChecked = state
    }

    companion object{
        const val EXTRA_DATA_MOVIE = "extra_data_movie"
    }
}