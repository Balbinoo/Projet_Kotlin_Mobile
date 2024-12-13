package com.ismin.android

import OeuvreAdapter
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val OEUVRES = "oeuvres"

class OeuvreListFragmentLessDetail : Fragment() {
    private lateinit var oeuvres: ArrayList<Oeuvre>
    private lateinit var oeuvreAdapter: OeuvreAdapter
    private lateinit var recyclerView: RecyclerView

    // Define the interface
    interface OnFavoriteButtonClickListener {
        fun onPutFavoriteCreated(oeuvre: Oeuvre)
    }

    // Declare the listener
    private var listener: OnFavoriteButtonClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            oeuvres = it.getSerializable(OEUVRES) as ArrayList<Oeuvre>
        }
        setHasOptionsMenu(true)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Ensure the parent activity implements the listener interface
        if (context is OnFavoriteButtonClickListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFavoriteButtonClickListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_oeuvre_list_less_details, container, false)

        recyclerView = rootView.findViewById(R.id.f_oeuvre_list_rcv_oeuvres_lessDetails)
        oeuvreAdapter = OeuvreAdapter(oeuvres, listener)
        recyclerView.adapter = oeuvreAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        oeuvreAdapter.notifyDataSetChanged() // Force the repaint of the buttons

        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(oeuvres: ArrayList<Oeuvre>) =
            OeuvreListFragmentLessDetail().apply {
                arguments = Bundle().apply {
                    putSerializable(OEUVRES, oeuvres)
                }
            }
    }

}
