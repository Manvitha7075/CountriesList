package com.example.countrieslist.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countrieslist.R
import com.example.countrieslist.viewmodel.ApiState
import com.example.countrieslist.viewmodel.CountriesViewModel
import com.example.countrieslist.viewmodel.CountryViewModelProviderFactory


class CountriesFragment : Fragment() {

    private lateinit var viewModel: CountriesViewModel
    private lateinit var countriesAdapter: CountriesAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_countries, container, false)
        progressBar = view.findViewById(R.id.progressBar)
        recyclerView = view.findViewById(R.id.countriesRv)
        getCountries()
        return view

    }

    private fun getCountries() {
        val provider = CountryViewModelProviderFactory()
        viewModel = ViewModelProvider(this, provider).get(CountriesViewModel::class.java)
        viewModel.countryLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ApiState.Success -> {
                    progressBar.visibility = View.INVISIBLE
                    response.data?.let { newsResponse ->
                        recyclerView.layoutManager = LinearLayoutManager(this.activity)
                        countriesAdapter = CountriesAdapter(newsResponse)
                        recyclerView.adapter = countriesAdapter
                        Log.d("countyResponse", newsResponse.size.toString())
                    }
                }

                is ApiState.Error -> {
                    progressBar.visibility = View.INVISIBLE
                    response.message?.let { message ->
                        Toast.makeText(
                            this.activity,
                            "Sorry, Something went wrong. Please try again",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                is ApiState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        }

    }
}

