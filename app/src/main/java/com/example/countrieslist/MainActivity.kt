package com.example.countrieslist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countrieslist.model.CountriesList
import com.example.countrieslist.model.CountriesListItem
import com.example.countrieslist.repository.CountriesRepository
import com.example.countrieslist.viewmodel.ApiState
import com.example.countrieslist.viewmodel.CountriesViewModel
import com.example.countrieslist.viewmodel.CountryViewModelProviderFactory
import com.example.countrieslist.views.CountriesAdapter

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: CountriesViewModel
    lateinit var countriesAdapter: CountriesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val repository = CountriesRepository()
        val provider = CountryViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this,provider).get(CountriesViewModel::class.java)
        viewModel.countryPage.observe(this) { response ->
            when (response) {
                is ApiState.Success -> {
                    progressBar.visibility = View.INVISIBLE
                    // hideProgressBar()
                    response.data?.let { newsResponse ->
                        val recyclerView = findViewById<RecyclerView>(R.id.countriesRv)
                        recyclerView.layoutManager = LinearLayoutManager(this)

                        countriesAdapter = CountriesAdapter(newsResponse)
                        recyclerView.adapter = countriesAdapter
                        Log.d("countyResponse",newsResponse.size.toString())
                       // moviesAdapter.differ.submitList(newsResponse.items)
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

    fun setRecyclerView(context: Context, viewModel: CountriesViewModel){

//        countriesLi.apply {
//            adapter=moviesAdapter
//            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
//        }
    }
}