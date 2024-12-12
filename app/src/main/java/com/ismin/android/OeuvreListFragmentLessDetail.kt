package com.ismin.android

import OeuvreAdapter
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView

private const val OEUVRES = "oeuvres"

class OeuvreListFragmentLessDetail : Fragment() {
    private lateinit var oeuvres: ArrayList<Oeuvre>
    private lateinit var oeuvreAdapter: OeuvreAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            oeuvres = it.getSerializable(OEUVRES) as ArrayList<Oeuvre>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_oeuvre_list_less_details, container, false)

        recyclerView = rootView.findViewById(R.id.f_oeuvre_list_rcv_oeuvres_lessDetails)
        oeuvreAdapter = OeuvreAdapter(oeuvres)
        recyclerView.adapter = oeuvreAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

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
