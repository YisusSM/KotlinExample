package com.jesus.permisos.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jesus.permisos.*
import kotlinx.android.synthetic.main.activity_extension_function.*

class ExtensionFunctionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extension_function)
        buttonToast.setOnClickListener { toast("Extension function Toast") }
        buttonSnackbar.setOnClickListener {
            snackBar(
                "Extension function Snackbar",
                action = "Undo"
            ) { toast("Undo!!") }
        }
        buttonLoadByUrl.setOnClickListener { imageViewLoadByUrl.loadByUrl("https://i.ytimg.com/vi/9ZfN87gSjvI/maxresdefault.jpg") }
        buttonGoToActivity.setOnClickListener { goToActivity<MainActivity> { flags } }
    }
}