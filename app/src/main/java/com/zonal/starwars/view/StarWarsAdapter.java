package com.zonal.starwars.view;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zonal.starwars.R;
import com.zonal.starwars.model.DaoMaster;
import com.zonal.starwars.model.Planet;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter that handles basketable objects (e.g Basket Items and Vouchers)
 */
public class StarWarsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Planet> planetList = new ArrayList<>();
    private final LayoutInflater mLayoutInflater;

    StarWarsAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        final View lBasketableView = mLayoutInflater.inflate(R.layout.list_item_planet, parent, false);
        final StarWarsViewHolder starWarsViewHolder = new StarWarsViewHolder(lBasketableView);


        return starWarsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final StarWarsViewHolder starWarsViewHolder = (StarWarsViewHolder) holder;
        starWarsViewHolder.txtPlanetName.setText(planetList.get(position).getName());
        starWarsViewHolder.txtPlanetPopulation.setText("Population: " + planetList.get(position).getPopulation());
    }

    @Override
    public int getItemCount() {
        return planetList.size();
    }


    public void setPlanetList(List<Planet> items) {

        planetList.clear();
        planetList.addAll(items);
Log.d("API8","items "+items.size());
        // Notify the adapter that the data set has changed.
        notifyDataSetChanged();



    }


    static class StarWarsViewHolder extends RecyclerView.ViewHolder {


        TextView txtPlanetName;
        TextView txtPlanetPopulation;


        StarWarsViewHolder(View itemView) {
            super(itemView);

            txtPlanetName = itemView.findViewById(R.id.planetName);
            txtPlanetPopulation = itemView.findViewById(R.id.planetPopulation);

        }
    }
}
