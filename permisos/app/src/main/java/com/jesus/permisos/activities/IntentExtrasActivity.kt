package com.jesus.permisos.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.jesus.permisos.R
import com.jesus.permisos.ToolBarActivity
import com.jesus.permisos.models.Student
import kotlinx.android.synthetic.main.activity_intent_extras.*


class IntentExtrasActivity : ToolBarActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_extras)


        toolbarToLoad(tooolbar as Toolbar)
        enabledHomeDisplay(true)

        btnBack.setOnClickListener { startActivity(Intent(this, IntentsActivity::class.java)) }
        val isExtraSet = setIntentExtrasFromPreviousActivity()
        val isParcelableSet = setParcelableExtraFromPreviousActivity()

        if (!isExtraSet && !isParcelableSet) checkBoxDeveloper.visibility = View.INVISIBLE
    }

    private fun setIntentExtrasFromPreviousActivity(): Boolean {
        val name = intent.getStringExtra("name")
        val lastname = intent.getStringExtra("lastname")
        val age = intent.getIntExtra("age", 0)
        val developer = intent.getBooleanExtra("developer", true)

        if (name != null && lastname != null && age >= 0) {
            textViewName.text = name
            textViewLastName.text = lastname
            textViewAge.text = age.toString()
            checkBoxDeveloper.text = "developer"
            checkBoxDeveloper.isChecked = developer
            return true
        } else checkBoxDeveloper.visibility = View.INVISIBLE
        return false
    }

    private fun setParcelableExtraFromPreviousActivity(): Boolean {
        val student = intent.getParcelableExtra<Student>("student")
        student?.let {
            textViewName.text = student.name
            textViewLastName.text = student.lastName
            textViewAge.text = student.age.toString()
            checkBoxDeveloper.text = "developer"
            checkBoxDeveloper.isChecked = student.isDeveloper
            return true
        }
        return false
    }
}