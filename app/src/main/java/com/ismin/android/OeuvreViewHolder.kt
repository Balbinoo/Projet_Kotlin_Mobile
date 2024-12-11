package com.ismin.android

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OeuvreViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {

    var txvIdOeuvre = rootView.findViewById<TextView>(R.id.r_oeuvre_txv_id_oeuvre)
    var txvIdExposition = rootView.findViewById<TextView>(R.id.r_oeuvre_txv_id_exposition)
    var txvTitre = rootView.findViewById<TextView>(R.id.r_oeuvre_txv_titre)
    var txvAnnee = rootView.findViewById<TextView>(R.id.r_oeuvre_txv_annee)
    var imgPhotoUrl2 = rootView.findViewById<ImageView>(R.id.r_oeuvre_img_PhotoUrl2)
    val detailButton: Button = itemView.findViewById(R.id.r_oeuvre_btn_details) // Button declaration

}