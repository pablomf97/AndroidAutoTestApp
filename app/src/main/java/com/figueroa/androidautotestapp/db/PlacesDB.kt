package com.figueroa.androidautotestapp.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.figueroa.androidautotestapp.places.PlaceInfo

class PlacesDB(
        private val context: Context,
        private val DB_NAME: String = "Places.db",
        private val DB_VERSION: Int = 1,
        private val PLACES_TABLE: String = "CREATE TABLE places" +
                "(title VARCHAR(20), address VARCHAR(50), " +
                "description VARCHAR(50), phoneNumber VARCHAR(20))") :
        SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(PLACES_TABLE)
                ?: Toast.makeText(
                        context,
                        "Could not create database",
                        Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS places") ?: return
        onCreate(db)
    }

    fun getAllPlaces() : List<PlaceInfo> {
        var res : MutableList<PlaceInfo> = mutableListOf()
        val cursor : Cursor
        val fieldsToRetrieve = arrayOf("title", "address", "description", "phoneNumber")

        try {
            val db: SQLiteDatabase = readableDatabase

            cursor = db.query("places", fieldsToRetrieve, null,
                    null, null, null, null, null)
            cursor.moveToFirst()

            res = mutableListOf()
            if (cursor.count > 0) {
                while (!cursor.isAfterLast) {
                    res.add(
                            PlaceInfo(cursor.getString(0),
                                    cursor.getString(1),
                                    cursor.getString(2),
                                    cursor.getString(3))
                    )
                    cursor.moveToNext()
                }
            }

            db.close()
            cursor.close()
        } catch (exception: Exception) {
            Toast.makeText(
                    context,
                    "Could not retrieve places",
                    Toast.LENGTH_LONG).show()
        }

        return res.toList()
    }

    fun addPlace(placeInfo: PlaceInfo) : Boolean {
        var res = false
        val values : ContentValues?

        try {
            if (getAllPlaces().size < 6) {
                val db = writableDatabase

                values = ContentValues()
                values.put("title", placeInfo.title)
                values.put("address", placeInfo.address)
                values.put("description", placeInfo.description)
                values.put("phoneNumber", placeInfo.phoneNumber)

                db.insert("places", null, values)
                db.close()

                res = true
            } else
                Toast.makeText(
                        context,
                        "You can only insert 6 places to the DB",
                        Toast.LENGTH_LONG).show()
        } catch (exception: Exception) {
            Toast.makeText(
                    context,
                    "Could not add place to the database",
                    Toast.LENGTH_LONG
            ).show()
        }

        return res
    }

    fun deletePlace(placeInfo: PlaceInfo) : Boolean {
        var res = false

        try {
            val db = writableDatabase

            db.delete("places",
                    "title = '${placeInfo.title}'",
                    null)

            db.close()

            res = true
        } catch (exception: Exception) {
            Toast.makeText(
                    context,
                    "Could not delete place from the database",
                    Toast.LENGTH_LONG
            ).show()
        }

        return res
    }
}