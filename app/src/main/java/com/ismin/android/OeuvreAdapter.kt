package com.ismin.android
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class OeuvreAdapter(private var oeuvres: List<Oeuvre>) : RecyclerView.Adapter<OeuvreViewHolder>() {

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
            showDetails(holder.detailButton.context, oeuvre)
        }
    }

    private fun showDetails(context: android.content.Context, oeuvre: Oeuvre) {
        Log.d("Button Pressed!", "Details button clicked for Oeuvre: ${oeuvre.titre}")

        // Show a Toast message
        Toast.makeText(
            context, // Context from the button
            "Details button clicked for: ${oeuvre.titre}", // Message to display
            Toast.LENGTH_SHORT // Duration of the message
        ).show()
    }

    override fun getItemCount(): Int {
        return oeuvres.size
    }

    fun updateOeuvres(allOeuvres: List<Oeuvre>) {
        oeuvres = allOeuvres
        notifyDataSetChanged()
    }
}
