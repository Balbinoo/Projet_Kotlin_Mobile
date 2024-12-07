package com.ismin.android

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OeuvreViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {

    var txvIdOeuvre = rootView.findViewById<TextView>(R.id.r_oeuvre_txv_id_oeuvre)
    var txvIdExposition = rootView.findViewById<TextView>(R.id.r_oeuvre_txv_id_exposition)

    var txvTitre = rootView.findViewById<TextView>(R.id.r_oeuvre_txv_titre)
    var txvAnnee = rootView.findViewById<TextView>(R.id.r_oeuvre_txv_annee)
    var txvDimension = rootView.findViewById<TextView>(R.id.r_oeuvre_txv_dimension)
    var txvMatiere = rootView.findViewById<TextView>(R.id.r_oeuvre_txv_matiere)
    var txvCollection = rootView.findViewById<TextView>(R.id.r_oeuvre_txv_collection)
    var txvTextePresentation = rootView.findViewById<TextView>(R.id.r_oeuvre_txv_texte_presentation)
    var txvEmplacement = rootView.findViewById<TextView>(R.id.r_oeuvre_txv_emplacement)
    var txvPhotoAuteur = rootView.findViewById<TextView>(R.id.r_oeuvre_txv_photo_auteur)
    var txvOeuvreRepereMaps = rootView.findViewById<TextView>(R.id.r_oeuvre_txv_repere_maps)
    var txvLat = rootView.findViewById<TextView>(R.id.r_oeuvre_txv_lon)
    var txvLon = rootView.findViewById<TextView>(R.id.r_oeuvre_txv_lat)
    //var txvPhotoUrl2 = rootView.findViewById<imageView>(R.id.r_oeuvre_img_PhotoUrl2_photo_url2)
    var imgPhotoUrl2 = rootView.findViewById<ImageView>(R.id.r_oeuvre_img_PhotoUrl2_photo_url2)



    var txvPhotoNomfichier = rootView.findViewById<TextView>(R.id.r_oeuvre_txv_photo_nomfichier)



}