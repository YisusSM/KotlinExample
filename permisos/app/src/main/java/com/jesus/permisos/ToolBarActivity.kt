package com.jesus.permisos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import com.jesus.permisos.models.IToolbar

open class ToolBarActivity : AppCompatActivity(), IToolbar {
    protected var toolbar: androidx.appcompat.widget.Toolbar? = null
    override fun toolbarToLoad(toolbar: androidx.appcompat.widget.Toolbar?) {
        this.toolbar = toolbar
        this.toolbar?.let { setSupportActionBar(this.toolbar) }
    }

    override fun enabledHomeDisplay(value: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(value)
    }
}