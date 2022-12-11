package com.example.crypto.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.crypto.R
import com.example.crypto.databinding.ActivityCryptoBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class CryptoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCryptoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_crypto)
        //setContentView(R.layout.activity_crypto)

        //setSupportActionBar(binding.toolbar)

        val navController = this.findNavController(R.id.navHostFragment)
        // To implement Up button
        //NavigationUI.setupActionBarWithNavController(this, navController)
        val bottomNav = binding.bottomNavigationView

        bottomNav.setupWithNavController(navController)

        // Hide bottom nav on screens which don't require it
        lifecycleScope.launchWhenResumed {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                bottomNav.visibility = when (destination.id) { // implement bottomNav.hide() / bottomNav.show() with animation
                    R.id.detailFragment, R.id.exchangeFragment, R.id.webViewFragment, R.id.newsDetailFragment -> View.GONE
                    else -> View.VISIBLE
                }
            }
        }
    }

    // TODO: Change color of exchange trust score drawables with pixolor
    //  Change colors & appearance in exchange fragment
    //  Implement swipe refresh layout on *required screens
    //  Add recyclerview headers
    //  Change colors & appearance in search results fragment
    //  Fix News API issue - change to better API
    //  Implement websockets to refresh market data in periodic intervals

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHostFragment)
        return navController.navigateUp()
    }
}