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

class MainActivity : AppCompatActivity(), OeuvreCreator,OeuvreListFragmentLessDetail.OnFavoriteButtonClickListener {

    private var menu: Menu? = null
    private val compositionOeuvres = CompositionOeuvres()
    private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL).build()
    private val oeuvreService = retrofit.create(OeuvreService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        displayMainFragment()
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
    override fun onPutFavoriteCreated(oeuvre: Oeuvre) {

        Log.d("put", "inside onPutFavoriteCreated  ${oeuvre.id_oeuvre}")
        // Toggle the 'favorite' status of the Oeuvre
        oeuvre.favorite = !oeuvre.favorite
        // Proceed with API request
        oeuvreService.putFavorite(oeuvre.id_oeuvre, oeuvre)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@MainActivity,
                            if (oeuvre.favorite) "Added to Favorites" else "Removed from Favorites",
                            Toast.LENGTH_SHORT).show()
                            Log.d("put", "Deu bom dentro mesmo?: ${response.code()}, ${response.errorBody()?.string()}")

                    } else {
                        Toast.makeText(this@MainActivity, "Failed to update favorite status", Toast.LENGTH_SHORT).show()
                        Log.e("put", "Response error: ${response.code()}, ${response.errorBody()?.string()}")
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    Log.e("put", "Request failed: ${t.message}")
                }
            })
    }

    override fun onDeleteCreated(oeuvre: Oeuvre) {
        val idOeuvre = oeuvre.id_oeuvre
        if (idOeuvre.isBlank()) {
            Toast.makeText(this, "Invalid ID", Toast.LENGTH_SHORT).show()
            return
        }
        // Call the delete API with the correct id_oeuvre
        oeuvreService.deleteOeuvre(idOeuvre).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {

                    // Remove the oeuvre from the local data structure
                    compositionOeuvres.removeOeuvre(oeuvre)
                    // Refresh the list in the UI
                    displayOeuvreListFragment()

                    Toast.makeText(this@MainActivity, "Oeuvre deleted successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Failed to delete oeuvre", Toast.LENGTH_SHORT).show()
                    Log.e("Delete API", "Response error: ${response.code()}, ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("Delete API", "Request failed: ${t.message}")
            }
        })
    }

    private fun displayAllData() {
        oeuvreService.getAllOeuvres().enqueue(object : Callback<List<Oeuvre>> {

            override fun onResponse(call: Call<List<Oeuvre>>, response: Response<List<Oeuvre>>) {
                // Get the list of all oeuvres
                val allOeuvres: List<Oeuvre>? = response.body()
                // Add valid oeuvres to CompositionOeuvres
                if (allOeuvres != null) {
                    for (oeuvre in allOeuvres) {
                        Log.d("display", "${oeuvre.favorite} ${oeuvre.titre}")
                    }
                    allOeuvres.forEach { compositionOeuvres.addOeuvre(it) }
                }
                // Display the list of oeuvres
                displayOeuvreListFragment()
            }

            override fun onFailure(call: Call<List<Oeuvre>>, t: Throwable) {
                Toast.makeText(baseContext, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun displayOeuvreListFragment() {
        // Declares the fragment transaction
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // Get the list of oeuvres and sort them by id_oeuvre
        val sortedOeuvres = compositionOeuvres.getAllOeuvres().sortedByDescending { it.favorite }.toMutableList()

        for (oeuvre in sortedOeuvres) {
            Log.d("put", "${oeuvre.favorite} ${oeuvre.titre}")
        }

        // Declares new fragment listfragmentLessDetail
        val oeuvreListFragmentLessDetail = OeuvreListFragmentLessDetail.newInstance(
            ArrayList(sortedOeuvres),
        )
        // Replaces the actual fragment with the one created
        fragmentTransaction.replace(R.id.a_main_fragment_container, oeuvreListFragmentLessDetail)
        fragmentTransaction.commit()
    }

    private fun displayInfo() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val oeuvreInfoFragment = InfoFragment()
        fragmentTransaction.replace(R.id.a_main_fragment_container, oeuvreInfoFragment)
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
        fragmentTransaction.commit()
    }

    private fun updateData() {
        // Clear the existing data in the local CompositionOeuvres
        compositionOeuvres.clear()

        // Fetch the latest data from the server
        oeuvreService.getAllOeuvres().enqueue(object : Callback<List<Oeuvre>> {
            override fun onResponse(call: Call<List<Oeuvre>>, response: Response<List<Oeuvre>>) {
                if (response.isSuccessful) {
                    val updatedOeuvres: List<Oeuvre>? = response.body()
                    // Populate the local storage with the fetched data
                    if (updatedOeuvres != null) {
                        updatedOeuvres.forEach { compositionOeuvres.addOeuvre(it) }
                    }

                    // Refresh the UI with the updated data
                    displayOeuvreListFragment()
                    Toast.makeText(this@MainActivity, "Data updated successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Failed to update data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Oeuvre>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error updating data: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun displayCreateOeuvreFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val createOeuvreFragment = CreateOeuvreFragment()
        fragmentTransaction.replace(R.id.a_main_fragment_container, createOeuvreFragment)
        fragmentTransaction.commit()
    }

    private fun displayDeleteOeuvreFragment (){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val deleteOeuvreFragment = DeleteOeuvreFragment(compositionOeuvres)
        fragmentTransaction.replace(R.id.a_main_fragment_container, deleteOeuvreFragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        this.menu = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_main -> {
                displayMainFragment()
                true
            }
            R.id.action_delete -> {
                displayDeleteOeuvreFragment ()
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
                updateData()
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
