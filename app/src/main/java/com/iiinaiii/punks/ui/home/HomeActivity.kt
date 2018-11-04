package com.iiinaiii.punks.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.iiinaiii.punks.R
import com.iiinaiii.punks.dagger.inject
import com.iiinaiii.punks.databinding.ActivityHomeBinding
import com.iiinaiii.punks.domain.model.Beer
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

        setupRecyclerView()

        viewModel.uiModel.observe(this, Observer { uiModel ->
            updateBeers(uiModel.beers)
        })
        viewModel.loadBeers()
    }

    private fun setupRecyclerView() {
        beersAdapter = BeersAdapter(this)
        binding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = beersAdapter
        }
    }

    private fun updateBeers(beers: List<Beer>) {
        beersAdapter.also {
            it.beers.addAll(beers)
            it.notifyDataSetChanged()
        }
    }
}
