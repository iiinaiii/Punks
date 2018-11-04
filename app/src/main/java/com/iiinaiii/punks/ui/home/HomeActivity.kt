package com.iiinaiii.punks.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.iiinaiii.punks.R
import com.iiinaiii.punks.dagger.inject
import com.iiinaiii.punks.databinding.ActivityHomeBinding
import com.iiinaiii.punks.domain.model.Beer
import com.iiinaiii.punks.ui.InfiniteScrollListener
import com.iiinaiii.punks.util.delegates.contentView
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModel: HomeViewModel
    lateinit var beersAdapter: BeersAdapter

    private val binding by contentView<HomeActivity, ActivityHomeBinding>(
        R.layout.activity_home
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        setupRecyclerView()
        viewModel.uiModel.observe(this, Observer {
            val uiModel = it ?: return@Observer

            if (uiModel.showSuccess != null && !uiModel.showSuccess.consumed) {
                uiModel.showSuccess.consume()?.let { beerResult ->
                    updateBeers(beerResult.beers)
                }
            }

            if (uiModel.showError != null && !uiModel.showError.consumed) {
                uiModel.showError.consume()?.let {
                    showError()
                }
            }
        })
        viewModel.loadBeers()
    }

    private fun setupRecyclerView() {
        beersAdapter = BeersAdapter(this)
        binding.homeRecyclerView.apply {
            val linearLayoutManager = LinearLayoutManager(this@HomeActivity)
            layoutManager = linearLayoutManager
            adapter = beersAdapter
            addOnScrollListener(object : InfiniteScrollListener(linearLayoutManager, viewModel) {
                override fun onLoadMore() {
                    viewModel.loadBeers()
                }
            })
        }
    }

    private fun updateBeers(beers: List<Beer>) {
        beersAdapter.also {
            it.beers.addAll(beers)
            it.notifyDataSetChanged()
        }
    }

    private fun showError() {
        Snackbar.make(binding.root, R.string.beer_search_error, Snackbar.LENGTH_SHORT).show()
    }
}
