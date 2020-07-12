package com.jesus.drawer_recycler_card.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.jesus.drawer_recycler_card.R
import com.jesus.drawer_recycler_card.fragments.arrivalsFragment
import com.jesus.drawer_recycler_card.fragments.departuresFragment
import com.jesus.drawer_recycler_card.fragments.homeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ToolBarActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarToLoad(tooolbar as Toolbar)
        setNavDrawer()
        fragmentTransaction(homeFragment())
        navView.menu.getItem(0).isChecked = true //Cuando inicias la app deja marcado la primera opcion del navigator


    }

    private fun setNavDrawer() {
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> fragmentTransaction(homeFragment())
            R.id.nav_departures -> fragmentTransaction(departuresFragment())
            R.id.nav_arrivals -> fragmentTransaction(arrivalsFragment())
        }
        return true
    }
    private fun fragmentTransaction(fragment:Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container,fragment)
            .commit()
    }
}