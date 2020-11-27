package com.jfma75.mymovies.ui.detail

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.jfma75.mymovies.R
import com.jfma75.mymovies.databinding.DetailLayoutBinding
import com.jfma75.mymovies.di.DetailActivityComponent
import com.jfma75.mymovies.di.DetailActivityModule
import com.jfma75.mymovies.extensions.app
import com.jfma75.mymovies.extensions.getViewModel
import com.jfma75.mymovies.extensions.load
import com.jfma75.mymovies.extensions.viewBinding
import com.jfma75.mymovies.ui.common.ILogger

class DetailActivity : AppCompatActivity(), ILogger {
    private val viewBinding by viewBinding(DetailLayoutBinding::inflate)

    private lateinit var adapter: RatingsAdapter
    private lateinit var component: DetailActivityComponent
    private val viewModel by lazy { getViewModel { component.detailViewModel } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        component = app.component.plus(DetailActivityModule(intent.getStringExtra(MOVIE) ?: ""))

        viewModel.model.observe(this, Observer(::updateUI))
    }

    private fun updateUI(model: DetailViewModel.DetailUIModel) = with(model.movie) {
        viewBinding.movieDetailToolbar.title = title
        viewBinding.movieDetailSummary.text = plot

        val imageLoader = ImageLoader(this@DetailActivity) {
            componentRegistry {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    add(ImageDecoderDecoder())
                } else {
                    add(GifDecoder())
                }
            }
        }

        viewBinding.movieDetailImage.load(posterPath, imageLoader) {
            crossfade(true)
            placeholder(R.mipmap.loading)
        }
        viewBinding.movieDirector.text = director
        viewBinding.movieDuration.text = duration
        if (ratings != null) {
            adapter = RatingsAdapter()
            viewBinding.recyclerRatings.adapter = adapter

            adapter.ratings = this.ratings!!
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        const val MOVIE = "DetailActivity:movie"
    }
}