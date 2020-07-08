package com.jesus.permisos.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jesus.permisos.R
import com.jesus.permisos.app.preferences
import kotlinx.android.synthetic.main.activity_shared_preference.*

class SharedPreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)

            buttonSave.setOnClickListener { setValuesSharedPreferences() }
            getValuesSharedPreferences()
    }
    private fun getValuesSharedPreferences(){
        if(preferences!!.name!!.isNotEmpty() && preferences!!.age >= 0){
            textViewSharedPreference.text = "Welcome ${preferences!!.name}, your age is ${preferences!!.age}"
        }else{
            textViewSharedPreference.text = "Welcome. please save your age and your age"
        }
    }
    private fun setValuesSharedPreferences(){
            if (editTextName.text.toString().isNotEmpty() && editTextAge.text.toString().isNotEmpty()){
                preferences!!.name = editTextName.text.toString()
                preferences!!.age = editTextAge.text.toString().toInt()
                getValuesSharedPreferences();cleanEditText()
                makeToast("values have been saved successfully")
            }else{
                makeToast("please fill the name  and the age before saving")
            }
    }
    private fun cleanEditText(){
        editTextAge.text.clear()
        editTextName.text.clear()

    }
    private fun makeToast(msj:String) = Toast.makeText(this,msj,Toast.LENGTH_SHORT).show()
}