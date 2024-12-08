package com.ismin.android

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide

class OeuvreDetailActivity : AppCompatActivity() {

    private lateinit var titreTextView: TextView
    private lateinit var anneeTextView: TextView
    private lateinit var idOeuvreTextView: TextView
    private lateinit var idExpositionTextView: TextView
    private lateinit var photoImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_detail_oeuvre)

        // Initialize views
        titreTextView = findViewById(R.id.r_detail_titre)
        anneeTextView = findViewById(R.id.r_detail_annee)
        idOeuvreTextView = findViewById(R.id.r_detail_id_oeuvre)
        idExpositionTextView = findViewById(R.id.r_detail_id_exposition)
        photoImageView = findViewById(R.id.r_detail_img_PhotoUrl)

        // Get the data passed from the adapter
        val titre = intent.getStringExtra("titre")
        val annee = intent.getStringExtra("annee")
        val idOeuvre = intent.getStringExtra("id_oeuvre")
        val idExposition = intent.getStringExtra("id_exposition")
        val photoUrl = intent.getStringExtra("photo_url2")

        // Display the data
        titreTextView.text = titre
        anneeTextView.text = annee
        idOeuvreTextView.text = idOeuvre
        idExpositionTextView.text = idExposition

        // Load image using Glide
        Glide.with(this).load(photoUrl).into(photoImageView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu for the toolbar
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // Handle the menu item action
            R.id.action_info -> {
                // This will close OeuvreDetailActivity and return to the MainActivity
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
