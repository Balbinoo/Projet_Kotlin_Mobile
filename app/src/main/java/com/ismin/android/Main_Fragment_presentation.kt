package com.ismin.android
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class Main_Fragment_presentation : Fragment() {

    private var texte: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.main_fragment_presentation,container,false)
        //texte = rootView.findViewById(R.id.welcome_texte)

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clear the text in the TextView before the view is destroyed
        texte?.text = ""
        texte = null // Prevent memory leaks
    // Cleanup resources or listeners if any
    }

    override fun onDetach() {
        super.onDetach()
        // Additional cleanup if necessary
    }

}