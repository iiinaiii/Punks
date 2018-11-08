package com.iiinaiii.punks.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.iiinaiii.punks.R
import com.iiinaiii.punks.dagger.inject
import com.iiinaiii.punks.databinding.ActivityBeerDetailBinding
import com.iiinaiii.punks.util.delegates.contentView
import javax.inject.Inject

class BeerDetailActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModel: BeerDetailViewModel

    private val binding by contentView<BeerDetailActivity, ActivityBeerDetailBinding>(
        R.layout.activity_beer_detail
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val beerId = intent.getIntExtra(EXTRA_BEER_ID, -1)
        check(beerId != -1) {
            "Beer ID is invalid"
        }

        inject(beerId)

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        viewModel.uiModel.observe(this, Observer { uiModel ->
            binding.uiModel = uiModel
        })
    }

    companion object {
        private const val EXTRA_BEER_ID = "extra_beer_id"

        fun createIntent(context: Context, beerId: Int): Intent {
            return Intent(context, BeerDetailActivity::class.java)
                .putExtra(EXTRA_BEER_ID, beerId)
        }
    }
}
