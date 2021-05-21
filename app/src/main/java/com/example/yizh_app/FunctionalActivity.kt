package com.example.yizh_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.yizh_app.ui.fuctional.home.HomeFragment
import com.example.yizh_app.ui.fuctional.profile.ProfileFragment
import com.example.yizh_app.ui.fuctional.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class FunctionalActivity : AppCompatActivity() {


    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_functional)

//        navController =  findNavController(R.id.nav_host_fragment2)
        val navView = findViewById<BottomNavigationView>(R.id.nav_view)

        val homeFragment = HomeFragment()
        val settingsFragment = SettingsFragment()
        val profileFragment = ProfileFragment()

        makeCurrentFragment(homeFragment)
        navView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> makeCurrentFragment(homeFragment)
                R.id.navigation_settings -> makeCurrentFragment(settingsFragment)
                R.id.navigation_myaccount -> makeCurrentFragment(profileFragment)
            }
            true
        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home,
            R.id.navigation_settings,
            R.id.navigation_myaccount
        )
            .build()
//        val navController = Navigation.findNavController(this, R.id.nav_host_fragment2)
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
//        NavigationUI.setupWithNavController(navView, navController)
    }
    private fun makeCurrentFragment(fragment: Fragment) =
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment2, fragment)
                commit()
            }
}