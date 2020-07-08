package com.jesus.permisos

import android.content.Intent

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

import androidx.constraintlayout.widget.ConstraintLayout

import com.google.android.material.snackbar.Snackbar

import com.jesus.permisos.activities.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ToolBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarToLoad(tooolbar as Toolbar)


        Button_To_Cycle.setOnClickListener { goToLifeCycleActivity() }
        Button_To_Click.setOnClickListener { goToClicksEventActivity() }
        button_to_android_extensions.setOnClickListener { goToExtensionFunctionActivity() }
        button_to_intents.setOnClickListener { goToIntentsActivity() }
        button_to_list_view.setOnClickListener { goToListViewActivity() }
        button_to_permissions.setOnClickListener { goToPermissionsActivity() }
        button_to_Picasso.setOnClickListener { goToPicasso() }
        button_to_shared.setOnClickListener { goToSharedPreferenceActivity() }

        fun showToast() = Toast.makeText(this, "Hola from toast", Toast.LENGTH_LONG).show()
        fun showSnackbar() {
            val layout = findViewById<ConstraintLayout>(R.id.constraint)
            //Snackbar.make(layout,"Hola from Snackbar",10000).show()
            Snackbar.make(layout, "Hola from Snackbar", 5000).setAction("UNDO") {
                Snackbar.make(layout, "Email has been restored", Snackbar.LENGTH_SHORT).show()
            }.show()
        }
    }

    private fun goToLifeCycleActivity() = startActivity(Intent(this, LifeCycleActivity::class.java))
    private fun goToClicksEventActivity() =
        startActivity(Intent(this, ClicksEventActivity::class.java))

    private fun goToExtensionFunctionActivity() =
        startActivity(Intent(this, ExtensionFunctionActivity::class.java))

    private fun goToIntentsActivity() = startActivity(Intent(this, IntentsActivity::class.java))
    private fun goToListViewActivity() = startActivity(Intent(this, ListViewActivity::class.java))
    private fun goToPermissionsActivity() =
        startActivity(Intent(this, PermissionsActivity::class.java))

    private fun goToPicasso() = startActivity(Intent(this, PicassoActivity::class.java))
    private fun goToSharedPreferenceActivity() =
        startActivity(Intent(this, SharedPreferenceActivity::class.java))

}