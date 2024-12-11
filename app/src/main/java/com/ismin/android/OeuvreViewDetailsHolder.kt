package com.ismin.android

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OeuvreViewDetailsHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {

    var txvIdOeuvre = rootView.findViewById<TextView>(R.id.r_detail_id_oeuvre)
    var txvIdExposition = rootView.findViewById<TextView>(R.id.r_detail_id_exposition)
    var txvTitre = rootView.findViewById<TextView>(R.id.r_detail_titre)
    var txvAnnee = rootView.findViewById<TextView>(R.id.r_detail_annee)
    var imgPhotoUrl2 = rootView.findViewById<ImageView>(R.id.r_detail_img_PhotoUrl)
    var txvDimension = rootView.findViewById<TextView>(R.id.r_detail_dimension)
    var txvMatiere = rootView.findViewById<TextView>(R.id.r_detail_matiere)
    var txvCollection = rootView.findViewById<TextView>(R.id.r_detail_collection)
    var txvTextePresentation = rootView.findViewById<TextView>(R.id.r_detail_texte_presentation)
    var txvEmplacement = rootView.findViewById<TextView>(R.id.r_detail_emplacement)
    var txvPhotoAuteur = rootView.findViewById<TextView>(R.id.r_detail_PhotoAuteur)
    var txvOeuvreRepereMaps = rootView.findViewById<TextView>(R.id.r_detail_OeuvreRepereMaps)
    var txvPhotoNomfichier = rootView.findViewById<TextView>(R.id.r_detail_PhotoNomfichier)
}