package com.jesus.permisos

import android.content.Context

class MySharedPreferences(context: Context) {
    //Nombre del fichero
    private val fileName = "mis preferencias"

    //instancia del fichero
    private val prefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    //por cad una de las variables guardadas  en nuestro ficher
    var name: String?
    get() = prefs.getString("name","")
    set(value) = prefs.edit().putString("name",value).apply()

    var age: Int
        get() = prefs.getInt("age",-1)
        set(value) = prefs.edit().putInt("age", value).apply()


}