package com.ismin.android

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val SERVER_BASE_URL = "https://app-96fe8c94-9085-4eab-b051-80432bfa2281.cleverapps.io/"

class MainActivity : AppCompatActivity(), OeuvreCreator {

    private val compositionOeuvres = CompositionOeuvres()
    private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL).build()
    private val oeuvreService = retrofit.create(OeuvreService::class.java)

    override fun onOeuvreCreated(oeuvre: Oeuvre) {
        // I don't know what it does write on top of
        oeuvreService.createOeuvre(oeuvre)
            .enqueue {
                onResponse = {
                    val oeuvreFromServer: Oeuvre? = it.body()
                    compositionOeuvres.addOeuvre(oeuvreFromServer!!)
                    displayOeuvreListFragment()
                }
                onFailure = {
                    Toast.makeText(this@MainActivity, it?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_fragment)
    }

    private fun displayAllData(){
        oeuvreService.getAllOeuvres().enqueue(object : Callback<List<Oeuvre>> {

            override fun onResponse(call: Call<List<Oeuvre>>, response: Response<List<Oeuvre>>) {
                // Get the list of all oeuvres
                val allOeuvres: List<Oeuvre>? = response.body()

                // Add valid oeuvres to CompositionOeuvres
                if (allOeuvres != null) {
                    allOeuvres.forEach { compositionOeuvres.addOeuvre(it) }
                }

                // Display the list of oeuvres
                displayOeuvreListFragment()
            }

            override fun onFailure(call: Call<List<Oeuvre>>, t: Throwable) {
                Log.d("Error directly?",  "Did it get in here????")
                Toast.makeText(baseContext, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                Log.d("Retrofit", "Request URL: ${call.request().url()}")
                Log.e("MainActivity", "Error: ${t.message}")
            }
        })
    }

    private fun displayOeuvreListFragment() {
        //declares the fragment transaction
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        //declares new fragment listfragçentLessDetail
        val oeuvreListFragmentLessDetail = OeuvreListFragmentLessDetail.newInstance(
            compositionOeuvres.getAllOeuvres(),
        )
        //replaces the actual fragment with the one created
        fragmentTransaction.replace(R.id.a_info_lyt_container_oeuvre, oeuvreListFragmentLessDetail)
        fragmentTransaction.commit()
    }

    private fun displayInfo(){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val oeuvreinfoFragment = InfoFragment()
        fragmentTransaction.replace(R.id.a_info_lyt_container_oeuvre, oeuvreinfoFragment)
        fragmentTransaction.commit()
    }

    private fun displayMapsFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val mapsFragment = MapsFragment.newInstance(
            compositionOeuvres.getAllOeuvres(),
            )
        fragmentTransaction.replace(R.id.a_info_lyt_container_oeuvre, mapsFragment)
        fragmentTransaction.commit()
    }

    private fun displayCreateOeuvreFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val createOeuvreFragment = CreateOeuvreFragment()
        fragmentTransaction.replace(R.id.a_info_lyt_container_oeuvre, createOeuvreFragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                compositionOeuvres.clear()
                displayOeuvreListFragment()
                true
            }
            R.id.action_add -> {
                displayCreateOeuvreFragment()
                true
            }
            R.id.action_Data -> {
                displayAllData()
                true
            }
            R.id.action_update -> {
                //updateData()
                true
            }
            R.id.action_maps -> {
                displayMapsFragment()
                true
            }
            R.id.action_info -> {
                displayInfo()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
