package com.jesus.drawer_recycler_card

import androidx.appcompat.app.AppCompatActivity

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