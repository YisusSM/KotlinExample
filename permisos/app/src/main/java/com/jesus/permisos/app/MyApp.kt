package com.jesus.permisos.app

import android.app.Application
import com.jesus.permisos.MySharedPreferences

val preferences: MySharedPreferences? by lazy { MyApp.prefs!! }
class MyApp:Application(){
    companion object{
        var prefs : MySharedPreferences? = null
    }

    override fun onCreate() {
        super.onCreate()
        prefs = MySharedPreferences(applicationContext)

    }
}