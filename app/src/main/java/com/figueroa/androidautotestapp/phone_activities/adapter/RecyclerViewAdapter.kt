package com.figueroa.androidautotestapp.phone_activities.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.figueroa.androidautotestapp.R
import com.figueroa.androidautotestapp.db.PlacesDB
import com.figueroa.androidautotestapp.places.PlaceInfo

class RecyclerViewAdapter(private var places: MutableList<PlaceInfo>)
    : RecyclerView.Adapter<RecyclerViewAdapter.PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.place_recyclerview, parent, false)
        return PlaceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.itemView.setOnLongClickListener {
            val db = PlacesDB(it.context)
            if (db.deletePlace(places[position])) {
                Toast.makeText(
                    it.context,
                    "Place deleted!",
                    Toast.LENGTH_LONG
                ).show()

                places.clear()
                places.addAll(db.getAllPlaces())

                notifyDataSetChanged()
            } else {
                Toast.makeText(
                    it.context,
                    "Could not delete place",
                    Toast.LENGTH_LONG
                ).show()
            }
            true
        }
        holder.bindPlace(places[position])
    }

    override fun getItemCount(): Int {
        return places.size
    }

    class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var title: TextView = itemView.findViewById(R.id.recyclerview_place_title)
        private var address: TextView = itemView.findViewById(R.id.recyclerview_place_addr)

        fun bindPlace(placeInfo: PlaceInfo) {
            title.text = placeInfo.title
            address.text = placeInfo.address
        }

    }
}
