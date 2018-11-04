package com.iiinaiii.punks.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iiinaiii.punks.R
import com.iiinaiii.punks.databinding.ActivityHomeBinding
import com.iiinaiii.punks.util.delegates.contentView

class HomeActivity : AppCompatActivity() {

    private val binding by contentView<HomeActivity, ActivityHomeBinding>(
        R.layout.activity_home
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
