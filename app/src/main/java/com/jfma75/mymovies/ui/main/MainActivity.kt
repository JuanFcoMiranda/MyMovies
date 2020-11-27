package com.jfma75.mymovies.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.jfma75.mymovies.R
import com.jfma75.mymovies.databinding.ActivityMainBinding
import com.jfma75.mymovies.di.MainActivityComponent
import com.jfma75.mymovies.di.MainActivityModule
import com.jfma75.mymovies.extensions.*
import com.jfma75.mymovies.ui.common.ConnectionLiveData
import com.jfma75.mymovies.ui.common.ILogger
import com.jfma75.mymovies.ui.detail.DetailActivity

class MainActivity: AppCompatActivity(), ILogger {
    lateinit var connectionLiveData: ConnectionLiveData

    private lateinit var component: MainActivityComponent
    private val viewModel: MainViewModel by lazy { getViewModel { component.mainViewModel } }
    private lateinit var adapter: MoviesAdapter
    private val viewBinding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(viewBinding.toolbar)
        component = app.component.plus(MainActivityModule())

        setContentView(viewBinding.root)

        connectionLiveData = ConnectionLiveData(app)
        connectionLiveData.observe(this, { connected ->
            viewBinding.contentView.searchView.isEnabled = connected != null && connected
        })

        adapter = MoviesAdapter(viewModel::onMovieClicked)
        viewBinding.contentView.recycler.adapter = adapter

        viewModel.model.observe(this, Observer(::updateUI))
        /*viewModel.model.observe(this) { newValue ->
            updateUI()
        }*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        menuInflater.inflate(R.menu.menu_main, menu)
        viewBinding.contentView.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null && p0.isNotEmpty() && connectionLiveData.value != null && connectionLiveData.value!!) {
                    viewModel.onMovieSearchPerformed(p0)
                    viewBinding.contentView.searchView.closeKeyboard()
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
        viewBinding.contentView.progress.visibility =
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