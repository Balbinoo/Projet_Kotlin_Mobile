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
    val photo_url2: String,
    var isFavorite: Boolean = false
) : Serializable
