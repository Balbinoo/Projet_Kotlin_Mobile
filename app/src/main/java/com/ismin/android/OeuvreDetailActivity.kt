package com.ismin.android

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class OeuvreDetailActivity : AppCompatActivity() {

    private lateinit var viewHolder: OeuvreViewDetailsHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_oeuvre)

        // Initialize the ViewHolder with the root View of the Activity
        viewHolder = OeuvreViewDetailsHolder(findViewById(android.R.id.content))

        // Get the data passed from the adapter
        val oeuvre = intent.getSerializableExtra("oeuvre") as? Oeuvre

        val titre = oeuvre?.titre
        val annee = oeuvre?.annee
        val idOeuvre = oeuvre?.id_oeuvre
        val idExposition = oeuvre?.id_exposition
        val dimension = oeuvre?.dimension
        val matiere = oeuvre?.matiere
        val photoUrl = oeuvre?.photo_url2
        val collection = oeuvre?.collection
        val textePresentation = oeuvre?.texte_presentation
        val emplacement = oeuvre?.emplacement
        val photoAuteur = oeuvre?.photo_auteur
        val photoNomFichier = oeuvre?.photo_nomfichier
        val repereMaps = oeuvre?.oeuvre_repere_maps

        if (oeuvre != null) {
            Log.d("OeuvreDetailActivity", "id_oeuvre: ${oeuvre.id_oeuvre}, titre: ${oeuvre.titre}")
        }

        // Bind the data to the views using the ViewHolder
        viewHolder.txvTitre.text = titre
        viewHolder.txvAnnee.text = annee
        viewHolder.txvIdOeuvre.text = idOeuvre
        viewHolder.txvIdExposition.text = idExposition
        viewHolder.txvDimension.text = dimension
        viewHolder.txvMatiere.text = matiere
        viewHolder.txvCollection.text = collection
        viewHolder.txvTextePresentation.text = textePresentation
        viewHolder.txvEmplacement.text = emplacement
        viewHolder.txvPhotoAuteur.text = photoAuteur
        viewHolder.txvOeuvreRepereMaps.text = repereMaps
        viewHolder.txvPhotoNomfichier.text = photoNomFichier


        // Load image using Glide
        Glide.with(this).load(photoUrl).into(viewHolder.imgPhotoUrl2)
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
