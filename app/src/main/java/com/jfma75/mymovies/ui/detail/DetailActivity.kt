package com.jfma75.mymovies.ui.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import com.jfma75.mymovies.R
import com.jfma75.mymovies.di.DetailActivityComponent
import com.jfma75.mymovies.di.DetailActivityModule
import com.jfma75.mymovies.extensions.app
import com.jfma75.mymovies.extensions.getViewModel
import com.jfma75.mymovies.extensions.loadUrl
import com.jfma75.mymovies.ui.base.BaseActivity
import com.jfma75.mymovies.ui.common.ILogger
import kotlinx.android.synthetic.main.detail_layout.*

class DetailActivity : BaseActivity(), ILogger {
    override val contentResource: Int
        get() = R.layout.detail_layout

    private lateinit var adapter: RatingsAdapter
    private lateinit var component: DetailActivityComponent
    private val viewModel by lazy { getViewModel { component.detaiViewModel } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component = app.component.plus(DetailActivityModule(intent.getStringExtra(MOVIE)))

        viewModel.model.observe(this, Observer(::updateUI))
    }

    private fun updateUI(model: DetailViewModel.DetailUIModel) = with(model.movie) {
        movieDetailToolbar.title = title
        movieDetailSummary.text = plot
        movieDetailImage.loadUrl(posterPath)
        movieDirector.text = director
        movieDuration.text = duration
        if (ratings != null) {
            adapter = RatingsAdapter()
            recycler_ratings.adapter = adapter
            //recycler_ratings.layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.HORIZONTAL, false)

            adapter.ratings = this.ratings!!
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        const val MOVIE = "DetailActivity:movie"
    }
}