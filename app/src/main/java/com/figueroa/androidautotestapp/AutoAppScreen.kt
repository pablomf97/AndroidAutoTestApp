package com.figueroa.androidautotestapp

import com.figueroa.androidautotestapp.db.PlacesDB
import com.figueroa.androidautotestapp.places.PlaceInfo
import com.google.android.libraries.car.app.CarContext
import com.google.android.libraries.car.app.Screen
import com.google.android.libraries.car.app.model.*


class AutoAppScreen(carContext: CarContext) : Screen(carContext) {
    override fun getTemplate(): Template {

        val places = PlacesDB(carContext).getAllPlaces()

        val listBuilder = ItemList.builder()

        listBuilder.addItem(
            Row.builder()
                .setOnClickListener(ParkedOnlyOnClickListener.create {
                    screenManager.push(AutoAppScreen(carContext))
                    screenManager.pop()
                })
                .setTitle("Refresh!")
                .addText("Note that you can perform this action only when parked.")
                .build()
        )

        for (place in places) {
            listBuilder.addItem(
                Row.builder()
                    .setOnClickListener { onClick(place) }
                    .setTitle(place.title)
                    .addText(place.address)
                    .addText(place.description)
                    .build())
        }

        return ListTemplate.builder()
            .setSingleList(listBuilder.build())
            .setTitle("Saved places")
            .build()
    }

    private fun onClick(placeInfo: PlaceInfo?) {
        if (placeInfo != null)
            screenManager.push(PlaceDetailsScreen(carContext, placeInfo))
    }
}