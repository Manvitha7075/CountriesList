package com.example.countrieslist.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countrieslist.R
import com.example.countrieslist.repository.CountriesRepositoryImpl
import com.example.countrieslist.viewmodel.ApiState
import com.example.countrieslist.viewmodel.CountriesViewModel
import com.example.countrieslist.viewmodel.CountryViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: CountriesViewModel
    lateinit var countriesAdapter: CountriesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val repository = CountriesRepositoryImpl()
        val provider = CountryViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this,provider).get(CountriesViewModel::class.java)
        viewModel.countryLiveData.observe(this) { response ->
            when (response) {
                is ApiState.Success -> {
                    progressBar.visibility = View.INVISIBLE
                    // hideProgressBar()
                    response.data?.let { newResponse ->
                        val recyclerView = findViewById<RecyclerView>(R.id.countriesRv)
                        recyclerView.layoutManager = LinearLayoutManager(this)

                        countriesAdapter = CountriesAdapter(newResponse)
                        recyclerView.adapter = countriesAdapter
                        Log.d("countyResponse",newResponse.size.toString())
                    }
                }

                is ApiState.Error -> {
                    progressBar.visibility = View.INVISIBLE
//                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(this, "Sorry, Something went wrong. Please try again", Toast.LENGTH_LONG).show()
                        Log.d("countyResponse error",message)
                    }
                }

                is ApiState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    Log.d("countyResponse error","message")
                    // showProgressBar()
                }
            }
        }

    }
}