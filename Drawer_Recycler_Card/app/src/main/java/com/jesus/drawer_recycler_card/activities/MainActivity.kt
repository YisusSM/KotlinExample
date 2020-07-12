package com.jesus.drawer_recycler_card.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.WindowId
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.jesus.drawer_recycler_card.R
import com.jesus.drawer_recycler_card.fragments.arrivalsFragment
import com.jesus.drawer_recycler_card.fragments.departuresFragment
import com.jesus.drawer_recycler_card.fragments.homeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.view.*
import org.w3c.dom.Text

class MainActivity : ToolBarActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarToLoad(tooolbar as Toolbar)
        setNavDrawer()
        setUserInformation()


        if (savedInstanceState == null) {
            fragmentTransaction(homeFragment())
            navView.menu.getItem(0).isChecked =
                true //Cuando inicias la app deja marcado la primera opcion del navigator
        } 
    }

    private fun closeNavigate() = drawerLayout.closeDrawer(GravityCompat.START)

    private fun setUserInformation() {
        val name = navView.getHeaderView(0).textViewName
        val email = navView.getHeaderView(0).textViewEmail

        name?.let { name.text = getString(R.string.user_name) }
        email?.let { email.text = getString(R.string.user_email) }

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

    private fun loadFragmentById(id: Int) {
        when (id) {
            R.id.nav_home -> {
                fragmentTransaction(homeFragment())
                closeNavigate()
            }
            R.id.nav_departures -> {
                fragmentTransaction(departuresFragment())
                closeNavigate()
            }
            R.id.nav_arrivals -> {
                fragmentTransaction(arrivalsFragment())
                closeNavigate()
            }
        }
    }

    private fun showMessageNavItemSelectedById(id: Int) {
        when (id) {
            R.id.nav_profile -> makeToast("Profile")
            R.id.nav_settings -> makeToast("Settings")
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        showMessageNavItemSelectedById(item.itemId)
        loadFragmentById(item.itemId)

        return true
    }

    private fun fragmentTransaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    private fun makeToast(msj: String) {
        Toast.makeText(this, msj, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}