package com.jesus.permisos.activities


import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jesus.permisos.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_picasso.*

class PicassoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picasso)
        buttonLoader.setOnClickListener { loadImages() }
        Picasso.with(this).load("http://static.com/photos/288264/pexels-photo-288264.jpeg").fetch()
    }

    private fun loadImages() {
        Picasso.with(this).load("https://as.com/meristation/imagenes/2019/04/10/betech/1554933436_432016_1554933508_noticia_normal.jpg").fit().into(imageViewTop)
        Picasso.with(this).load("https://fondosmil.com/fondo/17010.jpg").fit().into(imageViewBottom)
    }
}