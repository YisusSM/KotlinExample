package com.jesus.permisos.models

import android.widget.Toolbar

interface IToolbar {
    fun toolbarToLoad(toolbar: androidx.appcompat.widget.Toolbar?)
    fun enabledHomeDisplay(value:Boolean)
}