package com.ismin.android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class OeuvreAdapter(
    private var oeuvres: List<Oeuvre>
) : RecyclerView.Adapter<OeuvreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OeuvreViewHolder {
        val rowView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_oeuvre_less_details, parent, false)
        return OeuvreViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: OeuvreViewHolder, position: Int) {
        val oeuvre = oeuvres[position]

        // Bind data to the ViewHolder
        holder.txvIdOeuvre.text = oeuvre.id_oeuvre
        holder.txvIdExposition.text = oeuvre.id_exposition
        holder.txvTitre.text = oeuvre.titre
        holder.txvAnnee.text = oeuvre.annee

        // Load image from URL using Glide
        Glide.with(holder.imgPhotoUrl2.context)
            .load(oeuvre.photo_url2)
            .into(holder.imgPhotoUrl2)

        // Set up button click listener
        holder.detailButton.setOnClickListener {
            // Call showDetails method and pass the necessary context
            showDetails(oeuvre, holder.itemView.context)
        }
    }

    private fun showDetails(oeuvre: Oeuvre, context: Context) {
        // Create an intent to launch OeuvreDetailActivity
        val intent = Intent(context, OeuvreDetailActivity::class.java)

        // Pass the data to the new activity using intent extras
        intent.putExtra("titre", oeuvre.titre)
        intent.putExtra("annee", oeuvre.annee)
        intent.putExtra("id_oeuvre", oeuvre.id_oeuvre)
        intent.putExtra("id_exposition", oeuvre.id_exposition)
        intent.putExtra("photo_url2", oeuvre.photo_url2) // Pass image URL

        // Start the activity
        context.startActivity(intent)
    }

    override fun getItemCount(): Int = oeuvres.size

    fun updateOeuvres(allOeuvres: List<Oeuvre>) {
        oeuvres = allOeuvres
        notifyDataSetChanged()
    }
}
