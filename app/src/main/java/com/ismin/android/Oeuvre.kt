package com.ismin.android

import GeoPoint2D
import java.io.Serializable

data class Oeuvre(
    val id_exposition: String,
    val id_oeuvre: String,
    val titre: String,
    val annee: String,
    val dimension: String,
    val matiere: String,
    val collection: String,
    val texte_presentation: String,
    val emplacement: String,
    val photo_nomfichier: String,
    val photo_auteur: String,
    val oeuvre_repere_maps: String,
    val geo_point_2d: GeoPoint2D,
    val photo_url2: String
) : Serializable {

    // Secondary constructor for creating Oeuvre with specific fields only
    constructor(
        id_exposition: String,
        id_oeuvre: String,
        titre: String,
        annee: String,
        photo_url2: String
    ) : this(
        id_exposition,
        id_oeuvre,
        titre,
        annee,
        "",  // Default empty value for dimension
        "",  // Default empty value for matiere
        "",  // Default empty value for collection
        "",  // Default empty value for texte_presentation
        "",  // Default empty value for emplacement
        "",  // Default empty value for photo_nomfichier
        "",  // Default empty value for photo_auteur
        "",  // Default empty value for oeuvre_repere_maps
        GeoPoint2D(0.0, 0.0), // Default value for geo_point_2d (adjust if needed)
        photo_url2
    )
}
