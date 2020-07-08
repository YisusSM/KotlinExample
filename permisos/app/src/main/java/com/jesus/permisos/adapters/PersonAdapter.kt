package com.jesus.permisos.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.jesus.permisos.inflate
import com.jesus.permisos.models.Persona
import kotlinx.android.synthetic.main.list_view_person.view.*

class PersonAdapter(val context: Context, val layout: Int, val list: List<Persona>) :
    BaseAdapter() {


    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, converView: View?, parent: ViewGroup): View {
        val view: View
        val vh: PersonViewHolder
        if (converView == null) {
            view = parent.inflate(layout)
            vh = PersonViewHolder(view)
            view.tag = vh
        } else {
            view = converView
            vh = view.tag as PersonViewHolder
        }

        val fullName ="${list[position].firstName}, ${list[position].lastName}"
        vh.fullName.text = fullName
        vh.age.text = list[position].age.toString()
        return view
    }
}

private class PersonViewHolder(view: View) {
    val fullName: TextView = view.txtViewName
    val age: TextView = view.txtViewAge
}