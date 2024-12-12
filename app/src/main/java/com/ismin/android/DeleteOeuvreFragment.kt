package com.ismin.android

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class DeleteOeuvreFragment(private val compositionOeuvres: CompositionOeuvres) : Fragment() {

    private lateinit var idOeuvre: EditText
    private lateinit var deleteBtn: Button
    private  lateinit var listener: OeuvreCreator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_delete_oeuvre,container,false)

        idOeuvre = rootView.findViewById(R.id.f_delete_id_input)
        deleteBtn = rootView.findViewById(R.id.f_delete_button)

        deleteBtn.setOnClickListener {
            deleteOeuvre()
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

    private fun deleteOeuvre() {
        val id_oeuvre = idOeuvre.text.toString()

        try {
            Toast.makeText(context, "IdDelete: $id_oeuvre", Toast.LENGTH_SHORT).show()

            Toast.makeText(context, "IdDelete: ${compositionOeuvres.getAllOeuvres()}", Toast.LENGTH_SHORT).show()
            val oeuvre = compositionOeuvres.getOeuvreOfIdOeuvre(id_oeuvre)

            listener.onDeleteCreated(oeuvre)
        } catch (e: Exception) {
            Toast.makeText(context, "Oeuvre not found", Toast.LENGTH_SHORT).show()
        }
    }


}