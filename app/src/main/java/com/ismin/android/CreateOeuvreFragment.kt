package com.ismin.android

import GeoPoint2D
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class CreateOeuvreFragment : Fragment() {

    private lateinit var idOeuvre: EditText
    private lateinit var idExposition: EditText
    private lateinit var leTitre: EditText
    private lateinit var lAnnee: EditText
    private lateinit var leMatiere: EditText
    private lateinit var lEmplacement: EditText
    private lateinit var leTextePresentation: EditText
    private lateinit var saveBtn: Button
    private  lateinit var listener: OeuvreCreator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_create_oeuvre,container,false)

        idOeuvre = rootView.findViewById(R.id.f_create_oeuvre_edt_oeuvre)
        idExposition = rootView.findViewById(R.id.f_create_oeuvre_edt_exposition)
        leTitre = rootView.findViewById(R.id.f_create_oeuvre_edt_titre)
        lAnnee = rootView.findViewById(R.id.f_create_oeuvre_edt_annee)
        leMatiere = rootView.findViewById(R.id.f_create_oeuvre_edt_matiere)
        lEmplacement = rootView.findViewById(R.id.f_create_oeuvre_edt_emplacement)
        leTextePresentation = rootView.findViewById(R.id.f_create_oeuvre_edt_texte_presentations)
        saveBtn = rootView.findViewById(R.id.f_create_oeuvre_btn_save)

        saveBtn.setOnClickListener {
            saveOeuvre()
        }

        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OeuvreCreator){
            listener = context
        } else {
            throw RuntimeException("$context must implements OeuvreCreator")
        }
    }

    private fun saveOeuvre() {

        val id_oeuvre = idOeuvre.text.toString()
        val id_exposition = idExposition.text.toString()
        val titre = leTitre.text.toString()
        val annee = lAnnee.text.toString()
        val matiere = leMatiere.text.toString()
        val emplacement = lEmplacement.text.toString()
        val textePresentation = leTextePresentation.text.toString()
        val photo_nomfichier = "Lupertz-Achille-LucBertrand.jpg"
        val photo_auteur = "Luc Bertrand / ADAGP 2022"
        val oeuvre_repere_maps = "mock data"
        val geo_point_2d = GeoPoint2D(47.902, 1.904)
        val photo_url2 = "https://github-production-user-asset-6210df.s3.amazonaws.com/54644626/394822720-53506455-da98-4937-8668-369a7aaecaee.jpeg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVCODYLSA53PQK4ZA%2F20241211%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20241211T160533Z&X-Amz-Expires=300&X-Amz-Signature=601829417749fd65e57a1736599caabfed8e14bff1fe43f82c5bd6c1716863c0&X-Amz-SignedHeaders=host"
        val dimension = "333 x 75,5 x 138 cm"
        val collection = "Collection?"

        val oeuvre = Oeuvre(
            id_exposition,
            id_oeuvre,
            titre,
            annee,
            dimension,
            matiere,
            collection,
            textePresentation,
            emplacement,
            photo_nomfichier,
            photo_auteur,
            oeuvre_repere_maps,
            geo_point_2d,
            photo_url2)

        listener.onOeuvreCreated(oeuvre)

    }

}