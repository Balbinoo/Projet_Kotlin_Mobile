package com.ismin.android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class Main_Fragment_presentation : Fragment() {

    private var texte: TextView? = null
    private var listener: OnStartButtonClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnStartButtonClickListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnStartButtonClickListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.main_fragment_presentation, container, false)

        val startButton: Button = rootView.findViewById(R.id.start_button)
        startButton.setOnClickListener {
            listener?.onStartButtonClicked()
        }

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
        listener = null
    }

    interface OnStartButtonClickListener {
        fun onStartButtonClicked()
    }
}
