package com.ad.thethetaprectical.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ad.thethetaprectical.R
import com.ad.thethetaprectical.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding

    private val homeFragment: Fragment = HomeFragment()
    private val profileFragment: Fragment = ProfileFragment()

    private val fm: FragmentManager = supportFragmentManager
    var active: Fragment = homeFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navView.setOnItemSelectedListener(this)

        fm.beginTransaction().add(R.id.nav_host_fragment_activity_home, profileFragment, "2")
            .hide(profileFragment).commit()
        fm.beginTransaction().add(R.id.nav_host_fragment_activity_home, homeFragment, "1").commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                fm.beginTransaction().hide(active).show(homeFragment).commit()
                active = homeFragment
                setTitle("Home")
                true
            }
            R.id.navigation_profile -> {
                fm.beginTransaction().hide(active).show(profileFragment).commit()
                active = profileFragment
                setTitle("Profile")
                true

            }
        }
        return true
    }
}