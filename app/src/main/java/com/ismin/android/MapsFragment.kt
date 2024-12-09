package com.ismin.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

private const val OEUVRES = "oeuvres"

class MapsFragment : Fragment(R.layout.maps_fragment), OnMapReadyCallback {

    private var googleMap: GoogleMap? = null
    private lateinit var oeuvres: ArrayList<Oeuvre>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the passed `oeuvres` data
        arguments?.let {
            oeuvres = it.getSerializable(OEUVRES) as ArrayList<Oeuvre>
        }

        // Ensure the SupportMapFragment is initialized when the fragment is created
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
            ?: SupportMapFragment.newInstance().also {
                childFragmentManager.beginTransaction()
                    .replace(R.id.map, it)
                    .commit()
            }

        // Set the OnMapReadyCallback listener
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Iterate through `oeuvres` to add markers
        for (oeuvre in oeuvres) {
            val location = LatLng(oeuvre.geo_point_2d.lat, oeuvre.geo_point_2d.lon) // Assuming `Oeuvre` has `latitude` and `longitude`
            map.addMarker(
                MarkerOptions()
                    .position(location)
                    .title(oeuvre.titre)
            )
        }

        // Optionally, move camera to the first oeuvre
        if (oeuvres.isNotEmpty()) {
            val firstLocation = LatLng(oeuvres[0].geo_point_2d.lat, oeuvres[0].geo_point_2d.lon)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLocation, 12f)) // Adjust zoom level
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        googleMap = null // Cleanup GoogleMap reference
    }

    companion object {
        @JvmStatic
        fun newInstance(oeuvres: ArrayList<Oeuvre>) =
            MapsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(OEUVRES, oeuvres)
                }
            }
    }
}
