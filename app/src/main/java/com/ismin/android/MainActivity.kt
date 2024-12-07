package com.ismin.android

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

    private val btnCreateOeuvre: FloatingActionButton by lazy {
        findViewById(R.id.a_main_btn_create_oeuvre)
    }

    private val txtWelcome: TextView by lazy {
        findViewById(R.id.a_main_txt_welcome)
    }

    private val btnGetAll: Button by lazy {
        findViewById(R.id.a_main_btn_getAll_oeuvre)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_oeuvre)

        btnGetAll.setOnClickListener {
            // Listen if button is pressed
            displayAllData()
        }

        btnCreateOeuvre.setOnClickListener {
            // Listen if button is pressed
            displayCreateOeuvreFragment()
        }
    }

    private fun displayAllData(){
        btnGetAll.visibility = View.INVISIBLE
        txtWelcome.visibility = View.INVISIBLE
        // Listen if button is pressed
        oeuvreService.getAllOeuvres().enqueue(object : Callback<List<Oeuvre>> {

            override fun onResponse(call: Call<List<Oeuvre>>, response: Response<List<Oeuvre>>) {
                Log.d("inside the oncreatee", "Did it get in here????")
                Log.d("MainActivity", "Response: ${response.body()}")

                // Get the list of all oeuvres
                val allOeuvres: List<Oeuvre>? = response.body()

                // Filter the list to remove invalid oeuvres
                val validOeuvres = allOeuvres?.filter { isValidOeuvre(it) } ?: emptyList()

                // Add valid oeuvres to CompositionOeuvres
                validOeuvres.forEach { compositionOeuvres.addOeuvre(it) }

                // Display the list of oeuvres
                displayOeuvreListFragment()
            }

            override fun onFailure(call: Call<List<Oeuvre>>, t: Throwable) {
                Log.d("Error directly?", "Did it get in here????")
                Toast.makeText(baseContext, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                Log.d("Retrofit", "Request URL: ${call.request().url()}")
                Log.e("MainActivity", "Error: ${t.message}")
            }
        })
    }

    private fun displayOeuvreListFragment() {
        // If it is fetched onCreate
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val oeuvreListFragment = OeuvreListFragment.newInstance(compositionOeuvres.getAllOeuvres())
        fragmentTransaction.replace(R.id.a_main_lyt_container_oeuvre, oeuvreListFragment)
        fragmentTransaction.commit()
        btnCreateOeuvre.visibility = View.VISIBLE
    }

    private fun displayCreateOeuvreFragment() {
        // If button is pressed
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val createOeuvreFragment = CreateOeuvreFragment()
        fragmentTransaction.replace(R.id.a_main_lyt_container_oeuvre, createOeuvreFragment)
        fragmentTransaction.commit()
        btnCreateOeuvre.visibility = View.GONE
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
            R.id.action_info -> {
                // Hide the data-related views and show the initial UI elements.
                if (txtWelcome.visibility == View.INVISIBLE) {
                    btnGetAll.visibility = View.VISIBLE
                    txtWelcome.visibility = View.VISIBLE
                    // If you want to clear the fragments or hide them:
                    supportFragmentManager.beginTransaction().remove(supportFragmentManager.findFragmentById(R.id.a_main_lyt_container_oeuvre)!!).commit()
                }
                true // Ensure a Boolean value is returned
            }
            else -> super.onOptionsItemSelected(item)
        }
    }




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

    // Validation function to check if the Oeuvre is valid
    private fun isValidOeuvre(oeuvre: Oeuvre): Boolean {
        // Ensure geo_point_2d is an object (not a malformed string) and other necessary fields
        val isGeoPointValid = oeuvre.geo_point_2d != null
                && oeuvre.geo_point_2d.lon != 0.0
                && oeuvre.geo_point_2d.lat != 0.0

        // Ensure required fields are not empty or malformed
        val isTitleValid = !oeuvre.titre.isNullOrEmpty()
        val isIdValid = !oeuvre.id_oeuvre.isNullOrEmpty()

        // Log invalid oeuvres
        if (!isGeoPointValid || !isTitleValid || !isIdValid) {
            Log.e("MainActivity", "Invalid Oeuvre: $oeuvre")
        }

        return isGeoPointValid && isTitleValid && isIdValid
    }
}
