package com.jesus.drawer_recycler_card.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jesus.drawer_recycler_card.R
import com.jesus.drawer_recycler_card.adapters.FlightAdapter
import com.jesus.drawer_recycler_card.functions.toast
import com.jesus.drawer_recycler_card.listeners.RecyclerFlightListener
import com.jesus.drawer_recycler_card.models.Flight
import kotlinx.android.synthetic.main.fragment_departures.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class departuresFragment : Fragment() {
    private val list: ArrayList<Flight> by lazy { getFlights() }
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: FlightAdapter
    private val layoutManager by lazy { LinearLayoutManager(context) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.setTitle(R.string.departures_fragment_title)
        val rootView = inflater.inflate(R.layout.fragment_departures, container, false)
        recycler = rootView.recyclerView as RecyclerView
        setRecyclerView()


        return rootView
    }

    private fun setRecyclerView() {
        recycler.setHasFixedSize(true)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.layoutManager = layoutManager
        adapter = (FlightAdapter(list, object : RecyclerFlightListener {
            override fun onClick(flight: Flight, position: Int) {
                activity?.toast("Let's go to ${flight.city}!!")
            }

            override fun onDelete(flight: Flight, position: Int) {
                list.remove(flight)
                adapter.notifyItemRemoved(position)
                activity?.toast("${flight.city} has been removed!!")
            }

        }))
        recycler.adapter = adapter
    }

    private fun getFlights(): ArrayList<Flight> {
        return object : ArrayList<Flight>() {
            init {
                add(Flight(1, "amsterdam", R.drawable.amsterdam))
                add(Flight(2, "babilonia", R.drawable.babilonia))
                add(Flight(3, "granada", R.drawable.granada))
                add(Flight(4, "new york", R.drawable.newyork))
                add(Flight(5, "taj mahal", R.drawable.tajmahal))
                add(Flight(6, "taj mahal", R.drawable.tajmahal))
                add(Flight(7, "francia", R.drawable.torreeiffel))
                add(Flight(8, "amsterdam", R.drawable.amsterdam))
                add(Flight(9, "babilonia", R.drawable.babilonia))
                add(Flight(10, "granada", R.drawable.granada))
                add(Flight(11, "new york", R.drawable.newyork))
                add(Flight(12, "taj mahal", R.drawable.tajmahal))
                add(Flight(13, "taj mahal", R.drawable.tajmahal))
                add(Flight(14, "francia", R.drawable.torreeiffel))
                add(Flight(15, "amsterdam", R.drawable.amsterdam))
                add(Flight(16, "babilonia", R.drawable.babilonia))
                add(Flight(17, "granada", R.drawable.granada))
                add(Flight(18, "new york", R.drawable.newyork))
                add(Flight(19, "taj mahal", R.drawable.tajmahal))
                add(Flight(20, "taj mahal", R.drawable.tajmahal))
                add(Flight(21, "francia", R.drawable.torreeiffel))
            }
        }
    }
}