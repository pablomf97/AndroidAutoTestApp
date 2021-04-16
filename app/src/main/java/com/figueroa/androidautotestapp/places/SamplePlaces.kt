package com.figueroa.androidautotestapp.places

import com.figueroa.androidautotestapp.places.PlaceInfo

class SamplePlaces {

    fun getSamplePlaces(): List<PlaceInfo> {
        val places: MutableList<PlaceInfo> = ArrayList()
        places.add(
                PlaceInfo(
                        "Google Kirkland",
                        "747 6th St South, Kirkland, WA 98033",
                        "Tinted resource vector",
                        "+14257395600",
                        )
        )

        places.add(
                PlaceInfo(
                        "Google Bellevue",
                        "1120 112th Ave NE, Bellevue, WA 98004",
                        "Image resource bitmap",
                        "+14252301301",
                        )
        )

        places.add(
                PlaceInfo(
                        "Google South Lake Union",
                        "1021 Valley St, Seattle, WA 98109",
                        "Colored text marker",
                        "+12065311800",
                        )
        )

        places.add(
                PlaceInfo(
                        "Google Seattle",
                        "601 N 34th St, Seattle, WA 98103",
                        "Image bitmap",
                        "+12068761800",
                        )
        )
        return places
    }
}