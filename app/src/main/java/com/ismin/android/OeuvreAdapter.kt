package com.ismin.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class OeuvreAdapter(private var oeuvres: List<Oeuvre>) : RecyclerView.Adapter<OeuvreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OeuvreViewHolder {
        val rowView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_oeuvre, parent, false)
        return OeuvreViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: OeuvreViewHolder, position: Int) {
        val oeuvre = oeuvres[position]
        holder.txvIdOeuvre.text = oeuvre.id_oeuvre
        holder.txvIdExposition.text = oeuvre.id_exposition
        holder.txvTitre.text = oeuvre.titre
        holder.txvAnnee.text = oeuvre.annee
        holder.txvCollection.text = oeuvre.collection
        holder.txvAnnee.text = oeuvre.annee
        holder.txvEmplacement.text = oeuvre.emplacement
        holder.txvMatiere.text = oeuvre.matiere
        holder.txvDimension.text = oeuvre.dimension
        holder.txvOeuvreRepereMaps.text = oeuvre.oeuvre_repere_maps
        holder.txvTextePresentation.text = oeuvre.texte_presentation
        holder.txvPhotoAuteur.text = oeuvre.photo_auteur
        holder.txvPhotoNomfichier.text = oeuvre.photo_nomfichier
        //holder.txvPhotoUrl2.text = oeuvre.photo_url2
    }

    override fun getItemCount(): Int {
        return oeuvres.size
    }

    fun updateOeuvres(allOeuvres: List<Oeuvre>){
        oeuvres = allOeuvres
        notifyDataSetChanged()
    }

}