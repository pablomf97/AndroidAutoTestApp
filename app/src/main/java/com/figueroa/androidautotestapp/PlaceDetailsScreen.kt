package com.figueroa.androidautotestapp

import android.content.Intent
import android.net.Uri
import com.figueroa.androidautotestapp.places.PlaceInfo
import com.google.android.libraries.car.app.CarContext
import com.google.android.libraries.car.app.Screen
import com.google.android.libraries.car.app.model.*
import com.google.android.libraries.car.app.model.Action.BACK


class PlaceDetailsScreen(carContext: CarContext, private val placeInfo: PlaceInfo) : Screen(carContext) {

    override fun getTemplate(): Template {
        val actions: MutableList<Action> = ArrayList()
        actions.add(
                Action.builder()
                        .setTitle("Navigate")
                        .setBackgroundColor(CarColor.BLUE)
                        .setOnClickListener(this::onClickNavigate)
                        .build())
        actions.add(
                Action.builder()
                        .setTitle("Dial")
                        .setOnClickListener(this::onClickDial)
                        .build())

        val paneBuilder = Pane.builder()
                .setActions(actions)
                .addRow(Row.builder().setTitle("Address")
                        .addText(placeInfo.address).build())
                .addRow(Row.builder().setTitle("Phone")
                        .addText(placeInfo.phoneNumber).build())

        return PaneTemplate.builder(paneBuilder.build())
                .setTitle(placeInfo.title)
                .setHeaderAction(BACK)
                .build()
    }

    private fun onClickNavigate() {
        val uri: Uri = Uri.parse("geo:0,0?q=" + placeInfo.address)
        val intent = Intent(CarContext.ACTION_NAVIGATE, uri)
        carContext.startCarApp(intent)
    }

    private fun onClickDial() {
        val uri: Uri = Uri.parse("tel:" + placeInfo.phoneNumber)
        val intent = Intent(Intent.ACTION_DIAL, uri)
        carContext.startCarApp(intent)
    }

}