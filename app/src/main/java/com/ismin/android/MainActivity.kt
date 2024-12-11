package com.ismin.android

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
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

    private var texte: TextView? = null

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
        //setContentView(R.layout.main_fragment_presentation)
        displayMainFragment()
        //texte = findViewById(R.id.a_text_main) // Initialize TextView
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
        fragmentTransaction.replace(R.id.a_main_fragment_container, oeuvreListFragmentLessDetail)
        fragmentTransaction.commit()
    }

    private fun displayInfo(){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val oeuvreinfoFragment = InfoFragment()
        fragmentTransaction.replace(R.id.a_main_fragment_container, oeuvreinfoFragment)
        fragmentTransaction.commit()
    }

    private fun displayMapsFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val mapsFragment = MapsFragment.newInstance(
            compositionOeuvres.getAllOeuvres(),
            )
        fragmentTransaction.replace(R.id.a_main_fragment_container, mapsFragment)
        fragmentTransaction.commit()
    }

    private fun displayMainFragment() {
        setContentView(R.layout.main_fragment)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val mainFragment = Main_Fragment_presentation() // Initialize MainFragment
         fragmentTransaction.replace(R.id.a_main_fragment_container, mainFragment)
        fragmentTransaction.commit() // Commit the transaction
    }

    private fun updateData(){
/*
        compositionOeuvres.clear()
        displayOeuvreListFragment()

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
            }*/
    }

    private fun displayCreateOeuvreFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val createOeuvreFragment = CreateOeuvreFragment()
        fragmentTransaction.replace(R.id.a_main_fragment_container, createOeuvreFragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_main -> {
                displayMainFragment()
                true
            }
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
                texte?.visibility = View.INVISIBLE // Hide the TextView
                displayAllData()
                true
            }
            R.id.action_update -> {
                //updateData(Oeuvre)
                true
            }
            R.id.action_maps -> {
                texte?.visibility = View.INVISIBLE // Hide the TextView

                displayMapsFragment()
                true
            }
            R.id.action_info -> {
                texte?.visibility = View.INVISIBLE // Hide the TextView
                displayInfo()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
