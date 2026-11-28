package com.familycare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.familycare.ui.health.HealthFragment
import com.familycare.ui.home.HomeFragment
import com.familycare.ui.nutrition.NutritionFragment
import com.familycare.ui.schedule.ScheduleFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_home_bottom_nav)

        val rootLayout = findViewById<android.view.View>(R.id.rootLayout)
        ViewCompat.setOnApplyWindowInsetsListener(rootLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        if (savedInstanceState == null) {
            switchFragment(HomeFragment())
        }

        bottomNav.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.nav_home      -> HomeFragment()
                R.id.nav_health    -> HealthFragment()
                R.id.nav_nutrition -> NutritionFragment()
                R.id.nav_schedule  -> ScheduleFragment()
                else               -> return@setOnItemSelectedListener false
            }
            switchFragment(fragment)
            true
        }
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
