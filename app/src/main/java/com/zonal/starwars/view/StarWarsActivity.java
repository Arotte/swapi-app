package com.zonal.starwars.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zonal.starwars.R;
import com.zonal.starwars.model.Planet;
import com.zonal.starwars.planet_details.PlanetDetailsActivity;
import com.zonal.starwars.presenter.StarWarsPresenter;
import com.zonal.starwars.presenter.StarWarsPresenterImpl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class StarWarsActivity extends AppCompatActivity implements StarWarsView, StarWarsAdapter.ItemClickListener {

    private StarWarsPresenter starWarsPresenter;
    private RecyclerView recyclerView;
    private StarWarsAdapter starWarsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starwars);
        recyclerView = findViewById(R.id.recyclerViewPlanets);
        setAdapter();
        setSortButton();

        starWarsPresenter = new StarWarsPresenterImpl(this, this);
    }

    private void setAdapter(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        starWarsAdapter = new StarWarsAdapter(this);
        starWarsAdapter.setClickListener(this);
        recyclerView.setAdapter(starWarsAdapter);
    }

    @Override
    public void setPlanets(List<Planet> planetList) {
        starWarsAdapter.setPlanetList(planetList);
    }

    @Override
    public void onPlanetItemClick(View view, int position) {
        // User tapped a planet item
        Intent intent = new Intent(StarWarsActivity.this, PlanetDetailsActivity.class);
        intent.putExtra("planetObject", starWarsAdapter.getItem(position));
        startActivity(intent);
    }

    private void setSortButton() {
        findViewById(R.id.btn_planets_sort_by_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starWarsAdapter.sortByName();
            }
        });
    }
}
