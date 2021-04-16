package com.figueroa.androidautotestapp.phone_activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.figueroa.androidautotestapp.R
import com.figueroa.androidautotestapp.db.PlacesDB
import com.figueroa.androidautotestapp.places.PlaceInfo

class AddPlace : AppCompatActivity(), View.OnClickListener {

    private lateinit var titleField : android.widget.EditText
    private lateinit var addrField : android.widget.EditText
    private lateinit var descriptionField : android.widget.EditText
    private lateinit var phoneField : android.widget.EditText

    private lateinit var saveButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_place)

        titleField = findViewById(R.id.title_field)
        addrField = findViewById(R.id.addr_field)
        descriptionField = findViewById(R.id.description_field)
        phoneField = findViewById(R.id.phone_field)

        saveButton = findViewById(R.id.save_place_button)
        saveButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == saveButton.id) {
            val title = titleField.text.toString()
            val addr = addrField.text.toString()
            val description = descriptionField.text.toString()
            val phone = phoneField.text.toString()

            if (title.isBlank() || addr.isBlank() ||
                description.isBlank() || phone.isBlank()) {
                Toast.makeText(applicationContext,
                    "You must fill in all the place details!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val success = PlacesDB(applicationContext).addPlace(
                    PlaceInfo(
                        title,
                        addr,
                        description,
                        phone
                    )
                )

                if (success) {
                    finish()
                    Toast.makeText(applicationContext,
                        "Saved!",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(applicationContext,
                        "Sorry, something went wrong!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

}