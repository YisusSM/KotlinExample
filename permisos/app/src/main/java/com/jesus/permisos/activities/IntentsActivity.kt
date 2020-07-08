package com.jesus.permisos.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.jesus.permisos.MainActivity
import com.jesus.permisos.R
import com.jesus.permisos.goToActivity
import com.jesus.permisos.models.Student
import kotlinx.android.synthetic.main.activity_intents.*

class IntentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intents)
        btnExtras.setOnClickListener { goIntentExtras() }
        btnFlags.setOnClickListener { goIntentFlags() }
        btnObject.setOnClickListener { goIntentObjects() }

    }

    private fun goIntentExtras() {
        goToActivity<IntentsActivity> {
            putExtra("name", "Alejandro")
            putExtra("lastname", "Lora")
            intent.putExtra("age", 27)
            intent.putExtra("developer", true)
        }
    }

    private fun goIntentFlags() {
        //intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY//No guarda el activity en el stack
        //intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        goToActivity<IntentsActivity> {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        //     finish()
    }

    private fun goIntentObjects() {
        val student = Student("Alberto", "Santos", 24, true)
        goToActivity<IntentsActivity> { putExtra("student", student) }
    }
}