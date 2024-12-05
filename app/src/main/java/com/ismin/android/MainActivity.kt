package com.ismin.android

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val SERVER_BASE_URL = "https://app-96fe8c94-9085-4eab-b051-80432bfa2281.cleverapps.io/"

class MainActivity : AppCompatActivity(), OeuvreCreator{

    private val compositionOeuvres = CompositionOeuvres()

    private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL).build()
    private val oeuvreService = retrofit.create(OeuvreService::class.java)

    private val btnCreateOeuvre: FloatingActionButton by lazy {
        findViewById(R.id.a_main_btn_create_oeuvre)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_oeuvre)

        oeuvreService.getAllOeuvres().enqueue(object : Callback<List<Oeuvre>>{
            override fun onResponse(call: Call<List<Oeuvre>>, response: Response<List<Oeuvre>>
            ) {
                val allOeuvres: List<Oeuvre>? = response.body()
                allOeuvres?.forEach{ compositionOeuvres.addOeuvre(it) }
                displayOeuvreListFragment()
            }

            override fun onFailure(call: Call<List<Oeuvre>>, t: Throwable) {
//                Toast.makeText(baseContext, "Quelque chose a pete", Toast.LENGTH_SHORT).show()
                Toast.makeText(baseContext, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                Log.d("Retrofit", "Request URL: ${call.request().url()}")



            }
        })

        btnCreateOeuvre.setOnClickListener{
            displayCreateOeuvreFragment()
        }
    }

    private fun displayOeuvreListFragment(){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val oeuvreListFragment = OeuvreListFragment.newInstance(compositionOeuvres.getAllOeuvres())
        fragmentTransaction.replace(R.id.a_main_lyt_container_oeuvre, oeuvreListFragment)
        fragmentTransaction.commit()
        btnCreateOeuvre.visibility = View.VISIBLE
    }

    private fun displayCreateOeuvreFragment(){
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        return when (item.itemId){
            R.id.action_delete -> {
                compositionOeuvres.clear()
                displayOeuvreListFragment()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onOeuvreCreated(oeuvre:Oeuvre){
        oeuvreService.createOeuvre(oeuvre)
            .enqueue {
                onResponse = {
                    val oeuvreFromServer: Oeuvre? = it.body()
                    compositionOeuvres.addOeuvre(oeuvreFromServer!!)
                    displayOeuvreListFragment()
                }
                onFailure = {
                    Toast.makeText(this@MainActivity,it?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
}