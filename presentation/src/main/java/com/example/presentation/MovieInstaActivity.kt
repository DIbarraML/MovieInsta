package com.example.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.presentation.databinding.ActivityMovieInstaBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MovieInstaActivity : AppCompatActivity() {

    lateinit var movieInstaBinding: ActivityMovieInstaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieInstaBinding = ActivityMovieInstaBinding.inflate(layoutInflater)
        setContentView(movieInstaBinding.root)

        val navView: BottomNavigationView = movieInstaBinding.bottomNavigation
        val navController = findNavController(R.id.fragmentContainer)
        navView.setupWithNavController(navController)
    }
}