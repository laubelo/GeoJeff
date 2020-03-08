package com.app.geojeff.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.geojeff.R
import com.app.geojeff.data.entities.City
import kotlinx.android.synthetic.main.item_list_cities.view.*

class CitiesAdapter(
    private val cities: List<City>,
    private val onCityClick: OnCityClick
) : RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_cities, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cities.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(cities[position])

        //hide the separator if the current item is the last position
        hideLastSeparator(holder, position)
    }

    private fun hideLastSeparator(holder: ViewHolder, position: Int) {
        holder.itemView.view_separator.visibility = View.VISIBLE
        if (position == (cities.size - 1)) {
            holder.itemView.view_separator.visibility = View.GONE
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(city: City) {
            itemView.apply {
                val text = city.name + ", " + city.countryCode
                text_city.text = text
                text_city.setOnClickListener {
                    onCityClick.onClick(city)
                }
            }
        }
    }

    interface OnCityClick {
        fun onClick(city: City?)
    }

}