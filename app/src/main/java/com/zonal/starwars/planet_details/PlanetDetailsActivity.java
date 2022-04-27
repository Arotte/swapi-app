package com.zonal.starwars.planet_details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.TextView;

import com.zonal.starwars.R;
import com.zonal.starwars.model.Planet;
import com.zonal.starwars.presenter.PlanetDetailsPresenter;
import com.zonal.starwars.presenter.PlanetDetailsPresenterImpl;
import com.zonal.starwars.view.PlanetDetailsView;

public class PlanetDetailsActivity extends AppCompatActivity implements PlanetDetailsView {

    private Planet planet;
    private PlanetDetailsPresenter planetDetailsPresenter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_details);

        getPlanetDetails();
        setView();

        planetDetailsPresenter = new PlanetDetailsPresenterImpl(this, this);
    }

    private void getPlanetDetails() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            planet = (Planet) bundle.getSerializable("planetObject");
        }
    }

    private void setView() {
        ((TextView) findViewById(R.id.tv_planet_details_planet_name)).setText(planet.getName());
        ((TextView) findViewById(R.id.tv_planet_details_population)).setText(planet.getPopulation());
        ((TextView) findViewById(R.id.tv_planet_details_climate)).setText(planet.getClimate());
        ((TextView) findViewById(R.id.tv_planet_details_terrain)).setText(planet.getTerrain());
        ((TextView) findViewById(R.id.tv_planet_details_orbital_period)).setText(planet.getOrbitalPeriod());
        ((TextView) findViewById(R.id.tv_planet_details_rotational_period)).setText(planet.getRotationPeriod());
        ((TextView) findViewById(R.id.tv_planet_details_surface_water)).setText(planet.getSurfaceWater());
        ((TextView) findViewById(R.id.tv_planet_details_diameter)).setText(planet.getDiameter());
        ((TextView) findViewById(R.id.tv_planet_details_gravity)).setText(planet.getGravity());
        ((TextView) findViewById(R.id.tv_planet_details_population)).setText(planet.getPopulation());

        if (swipeRefreshLayout == null)
            swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.planet_details_swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                planetDetailsPresenter.refreshPlanet(planet.getUrl());
            }
        });
    }

    @Override
    public void setPlanet(Planet planet) {
        this.planet = planet;
        setView();

        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(false);
    }
}