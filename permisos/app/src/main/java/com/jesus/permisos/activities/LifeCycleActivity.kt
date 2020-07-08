package com.jesus.permisos.activities


import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.jesus.permisos.R

class LifeCycleActivity : LifeCycleEventsActivity() {

    private var exitEnable = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)
    }

    override fun onBackPressed() {
        if (exitEnable){
            super.onBackPressed()
        }
        exitEnable = true
        Toast.makeText(this,"Click back again to exit this screen",Toast.LENGTH_SHORT).show()
        Handler().postDelayed(Runnable { exitEnable = false },2000)
    }
}