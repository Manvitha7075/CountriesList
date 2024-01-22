package com.example.countrieslist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.countrieslist.R
import com.example.countrieslist.domain.entity.CountriesEntity

class CountriesAdapter(val countriesList: List<CountriesEntity>) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val countryName:TextView = itemView.findViewById(R.id.name)
        val countryCode:TextView = itemView.findViewById(R.id.code)
        val countryCapital:TextView = itemView.findViewById(R.id.capital)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.countries_row_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val countryItem = countriesList[position]
        holder.countryName.setText(countryItem.name + ", "+countryItem.region)
        holder.countryCode.setText(countryItem.code)
        holder.countryCapital.setText(countryItem.capital)

    }

    override fun getItemCount(): Int {
     return  countriesList.size
    }

}