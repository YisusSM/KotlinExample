package com.jesus.drawer_recycler_card.listeners

import com.jesus.drawer_recycler_card.models.Flight

interface RecyclerFlightListener {
    fun onClick(flight: Flight, position: Int)
    fun onDelete(flight: Flight, position: Int)
}