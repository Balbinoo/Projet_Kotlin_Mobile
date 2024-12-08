package com.ismin.android

class CompositionOeuvres {

    private val storage = HashMap<String, Oeuvre>()

    fun addOeuvre(oeuvre: Oeuvre){
        storage[oeuvre.id_oeuvre] = oeuvre
    }

    fun getOeuvre(id_oeuvre: String): Oeuvre {
        return storage[id_oeuvre] ?: throw Exception("Oeuvre pas trouve")
    }

    fun getAllOeuvres(): ArrayList<Oeuvre> {
        return ArrayList(storage.values
            .sortedBy { oeuvre -> oeuvre.id_oeuvre })
    }

    fun getSpecificOeuvresData(): ArrayList<Oeuvre> {
        return ArrayList(storage.values.sortedBy { oeuvre -> oeuvre.id_oeuvre }.map { oeuvre ->
            // Create a new Oeuvre with only the necessary fields
            Oeuvre(
                id_exposition = oeuvre.id_exposition,
                id_oeuvre = oeuvre.id_oeuvre,
                titre = oeuvre.titre,
                annee = oeuvre.annee,
                photo_url2 = oeuvre.photo_url2
            )
        })
    }

    fun getOeuvreOf(id_exposition: String): List<Oeuvre> {
        return storage.filterValues { oeuvre -> oeuvre.id_exposition.equals(id_exposition) }
         .values
         .sortedBy { oeuvre -> oeuvre.id_exposition  }
    }

    fun getTotalNumberOfOeuvres(): Int {
        return storage.size
    }

    fun clear(){
        storage.clear()
    }

}