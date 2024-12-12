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

    fun getOeuvreOfIdOeuvre(id_oeuvre: String): Oeuvre {
        return storage[id_oeuvre] ?: throw Exception("Oeuvre not found with id_oeuvre: $id_oeuvre")
    }

    fun clear(){
        storage.clear()
    }

    fun removeOeuvre(oeuvre: Oeuvre) {
        storage.remove(oeuvre.id_oeuvre) // Remove by id_oeuvre
    }

}