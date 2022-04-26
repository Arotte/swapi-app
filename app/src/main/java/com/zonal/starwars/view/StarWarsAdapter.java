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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Adapter that handles basketable objects (e.g Basket Items and Vouchers)
 */
public class StarWarsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Planet> planetList = new ArrayList<>();
    private final LayoutInflater mLayoutInflater;
    private StarWarsAdapter.ItemClickListener mItemClickListener;

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

    public Planet getItem(int position) {
        return planetList.get(position);
    }

    public void setPlanetList(List<Planet> items) {
        planetList.clear();
        planetList.addAll(items);
        Log.d("API8","items "+items.size());
        // Notify the adapter that the data set has changed.
        notifyDataSetChanged();
    }

    class StarWarsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtPlanetName;
        TextView txtPlanetPopulation;

        StarWarsViewHolder(View itemView) {
            super(itemView);
            txtPlanetName = itemView.findViewById(R.id.planetName);
            txtPlanetPopulation = itemView.findViewById(R.id.planetPopulation);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) mItemClickListener.onPlanetItemClick(view, getAdapterPosition());
        }
    }

    public void setClickListener(StarWarsAdapter.ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onPlanetItemClick(View view, int position);
    }

    public void sortByName() {
        Collections.sort(planetList, new Comparator<Planet>(){
            public int compare(Planet planet1, Planet planet2){
                return planet1.getName().compareTo(planet2.getName());
            }
        });
        notifyDataSetChanged();
    }
}
