package com.example.expensetracker.ui.activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.expensetracker.R
import com.example.expensetracker.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.dashboardFragment,R.id.settingsFragment)
        )
        bottomNavigationView = findViewById(R.id.bottom_nav)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
