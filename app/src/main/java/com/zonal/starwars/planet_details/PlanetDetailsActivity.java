package com.zonal.starwars.planet_details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.zonal.starwars.R;
import com.zonal.starwars.model.Planet;

public class PlanetDetailsActivity extends AppCompatActivity {

    private Planet planet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_details);
        getPlanetDetails();
        setView();
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
    }
}