package com.figueroa.androidautotestapp.phone_activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.figueroa.androidautotestapp.R
import com.figueroa.androidautotestapp.db.PlacesDB
import com.figueroa.androidautotestapp.phone_activities.adapter.RecyclerViewAdapter
import com.figueroa.androidautotestapp.places.PlaceInfo
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PlacesList : AppCompatActivity() {

    private lateinit var places: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var data: MutableList<PlaceInfo>

    private lateinit var db: PlacesDB

    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places_list)

        db = PlacesDB(applicationContext)

        fab = findViewById(R.id.recyclerview_fab)
        fab.setOnClickListener {
            startActivity(Intent(applicationContext, AddPlace::class.java))
        }

        createRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        updateRecyclerView()
    }

    private fun updateRecyclerView() {
        data.clear()

        val placesAux = db.getAllPlaces()

        if (!placesAux.isNullOrEmpty()) {
            data.addAll(placesAux)
            adapter.notifyDataSetChanged()
        }
    }

    private fun createRecyclerView() {
        data = db.getAllPlaces().toMutableList()
        places = findViewById(R.id.places_list)

        adapter = RecyclerViewAdapter(data)

        places.adapter = adapter
        places.layoutManager = LinearLayoutManager(applicationContext)
    }
}