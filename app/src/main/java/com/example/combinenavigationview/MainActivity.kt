package com.example.combinenavigationview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val bottomNavView: BottomNavigationView = findViewById(R.id.navigationView)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_item_home, R.id.nav_item_blog, R.id.nav_item_app), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        bottomNavView.setupWithNavController(navController)
        bottomNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            item.setChecked(true)
            when (item.itemId) {
                R.id.homeFragment -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.nav_homeFragment)
                }
                R.id.directionFragment -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.nav_directionFragment)
                }
                R.id.commentFragment -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.nav_commentFragment)
                }
            }
            false
        }
}