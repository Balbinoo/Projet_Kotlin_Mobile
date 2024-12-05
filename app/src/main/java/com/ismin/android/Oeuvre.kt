package com.ismin.android

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
    val geo_point_2d: String,
    val photo_url2: String
) : Serializable

data class GeoPoint2D(
    val lon: Double,
    val lat: Double
) : Serializable
