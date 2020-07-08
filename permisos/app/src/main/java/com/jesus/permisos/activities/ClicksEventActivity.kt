package com.jesus.permisos.activities

import android.animation.TimeAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jesus.permisos.R
import kotlinx.android.synthetic.main.activity_clicks_event.*

class ClicksEventActivity : AppCompatActivity(),View.OnLongClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clicks_event)
        clickInLine()

        buttonClickMulti1.setOnLongClickListener(this)
        buttonClickMulti2.setOnLongClickListener(this)
        buttonClickMulti3.setOnLongClickListener(this)


    }

    fun xmlClick(view: View) {
        Toast.makeText(this, "Click by xml", Toast.LENGTH_SHORT).show()
    }

    private fun clickInLine() = buttonClickInLine.setOnClickListener { Toast.makeText(this,"Click in Line",Toast.LENGTH_SHORT).show() }

    override fun onLongClick(view: View): Boolean{
        when(view.id){
            R.id.buttonClickMulti1 -> Toast.makeText(this,"Click Multi 1",Toast.LENGTH_SHORT).show()
            R.id.buttonClickMulti2 -> Toast.makeText(this,"Click Multi 2",Toast.LENGTH_SHORT).show()
            R.id.buttonClickMulti3 -> Toast.makeText(this,"Click Multi 3",Toast.LENGTH_SHORT).show()
        }
        return true
    }
}