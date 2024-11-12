package com.example.ubicaciongeografica

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputLatitude = findViewById<EditText>(R.id.inputLatitude)
        val inputLongitude = findViewById<EditText>(R.id.inputLongitude)
        val buttonBuscar = findViewById<Button>(R.id.buttonBuscar)
        val mapImageView = findViewById<ImageView>(R.id.mapImageView)

        buttonBuscar.setOnClickListener {
            val latitude = inputLatitude.text.toString()
            val longitude = inputLongitude.text.toString()

            if (latitude.isNotEmpty() && longitude.isNotEmpty()) {
                val url = buildMapboxUrl(latitude, longitude)
                loadMapImage(url, mapImageView)
            } else {
                Toast.makeText(this, "Por favor ingresa la latitud y la longitud", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun buildMapboxUrl(latitude: String, longitude: String): String {
        return "https://api.mapbox.com/styles/v1/mapbox/satellite-streets-v12/static/geojson(%7B%22type%22%3A%22FeatureCollection%22%2C%22features%22%3A%5B%7B%22type%22%3A%22Feature%22%2C%22geometry%22%3A%7B%22type%22%3A%22Point%22%2C%22coordinates%22%3A%5B$longitude%2C$latitude%5D%7D%2C%22properties%22%3A%7B%22marker-color%22%3A%22%23FF0000%22%7D%7D%5D%7D)/$longitude,$latitude,14/600x400?access_token=pk.eyJ1IjoiamFuZG8tODUiLCJhIjoiY2trZGt2NWNjMDQ4NDJvczBpamQ4cXc5eSJ9.U2xM76b9foKMmzzzA6VZEg"
    }

    private fun loadMapImage(url: String, imageView: ImageView) {
        Glide.with(this)
            .load(url)
            .into(imageView)
    }
}