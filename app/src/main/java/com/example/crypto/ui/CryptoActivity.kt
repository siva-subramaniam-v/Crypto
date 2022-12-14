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
import com.example.crypto.util.hide
import com.example.crypto.util.show
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
                when (destination.id) { // implement bottomNav.hide() / bottomNav.show() with animation
                    R.id.coinFragment, R.id.exchangeFragment, R.id.newsDetailFragment -> bottomNav.hide()
                    else -> bottomNav.show()
                }
            }
        }
    }

    // TODO: Change colors & appearance in exchange fragment
    //  Change text color and & arrows indicating percentage_change_24h_text in list_item_overview
    //  Implement swipe refresh layout on *required screens
    //  Add recyclerview headers
    //  Fix News API issue - change to better API
    //  Implement websockets to refresh market data in periodic intervals
    //  Implement connectivity manager
    //  Implement gradient color

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHostFragment)
        return navController.navigateUp()
    }
}