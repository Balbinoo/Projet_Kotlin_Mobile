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

    fun getOeuvreOfIdExposition(id_exposition: String): List<Oeuvre> {
        return storage.filterValues { oeuvre -> oeuvre.id_exposition.equals(id_exposition) }
         .values
         .sortedBy { oeuvre -> oeuvre.id_exposition  }
    }

    fun getOeuvreOfIdOeuvre(id_oeuvre: String): Oeuvre {
        return storage[id_oeuvre] ?: throw Exception("Oeuvre not found with id_oeuvre: $id_oeuvre")
    }


    fun getTotalNumberOfOeuvres(): Int {
        return storage.size
    }

    fun clear(){
        storage.clear()
    }

    fun removeOeuvre(oeuvre: Oeuvre) {
        storage.remove(oeuvre.id_oeuvre) // Remove by id_oeuvre
    }



}