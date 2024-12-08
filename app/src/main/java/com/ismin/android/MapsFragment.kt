package com.ismin.android
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class MapsFragment  : Fragment() {

    private  lateinit var listener: OeuvreCreator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.maps_fragment,container,false)

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

}