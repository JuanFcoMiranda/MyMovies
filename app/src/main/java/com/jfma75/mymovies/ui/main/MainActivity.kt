package com.jfma75.mymovies.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.jfma75.mymovies.R
import com.jfma75.mymovies.di.MainActivityComponent
import com.jfma75.mymovies.di.MainActivityModule
import com.jfma75.mymovies.extensions.app
import com.jfma75.mymovies.extensions.closeKeyboard
import com.jfma75.mymovies.extensions.getViewModel
import com.jfma75.mymovies.extensions.startActivity
import com.jfma75.mymovies.ui.base.BaseActivity
import com.jfma75.mymovies.ui.common.ConnectionLiveData
import com.jfma75.mymovies.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity: BaseActivity() {
    lateinit var connectionLiveData: ConnectionLiveData
    private lateinit var component: MainActivityComponent
    private val viewModel: MainViewModel by lazy { getViewModel { component.mainViewModel } }
    private lateinit var adapter: MoviesAdapter

    override val contentResource: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)
        component = app.component.plus(MainActivityModule())

        connectionLiveData = ConnectionLiveData(app)
        connectionLiveData.observe(this, Observer(::enableSearch))

        adapter = MoviesAdapter(viewModel::onMovieClicked)
        recycler.adapter = adapter

        viewModel.model.observe(this, Observer(::updateUI))
    }

    private fun enableSearch(connected: Boolean?) {
        search_view.isEnabled = connected != null && connected
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        menuInflater.inflate(R.menu.menu_main, menu)
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null && p0.isNotEmpty() && connectionLiveData.value != null && connectionLiveData.value!!) {
                    viewModel.onMovieSearchPerformed(p0)
                    search_view.closeKeyboard()
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 == null || p0.isEmpty()) {
                    adapter.movies = emptyList()
                    adapter.notifyDataSetChanged()
                }
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun updateUI(model: MainViewModel.MainUIModel) {
        progress.visibility =
            if (model is MainViewModel.MainUIModel.Loading) View.VISIBLE else View.GONE
        when (model) {
            is MainViewModel.MainUIModel.Content -> {
                val movies = model.movies
                adapter.movies = movies
                adapter.notifyDataSetChanged()
            }
            is MainViewModel.MainUIModel.Navigation -> startActivity<DetailActivity> {
                putExtra(DetailActivity.MOVIE, model.movie.imdbID)
            }
        }
    }
}