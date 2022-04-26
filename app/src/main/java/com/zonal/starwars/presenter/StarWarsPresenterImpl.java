package com.zonal.starwars.presenter;


import android.app.Activity;
import android.os.Handler;
import android.os.Looper;


import com.zonal.starwars.StarWarsApp;
import com.zonal.starwars.model.DaoSession;
import com.zonal.starwars.model.Planet;
import com.zonal.starwars.view.StarWarsView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class StarWarsPresenterImpl implements StarWarsPresenter {
    private StarWarsView starWarsView;
    private Activity activity;
    private String API_URL = "https://swapi.dev/api/planets";

    public StarWarsPresenterImpl(StarWarsView starWarsView, Activity activity) {
        this.starWarsView = starWarsView;
        this.activity=activity;
        getPlanets(API_URL);
    }

    // =============================================================================================
    // API Interaction methods
    // =============================================================================================

    private void getPlanets(final String urlName) {
        Thread thread =  new Thread() {
            @Override
            public void run() {
                try {
                    URL  url = new URL(urlName);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    url.openStream()));
                    String inputLine;
                    StringBuilder planets = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        planets.append(inputLine);
                    }

                    JSONObject jsonObject = new JSONObject(planets.toString());
                    parsePlanetListResponse(jsonObject);

                    in.close();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public void refreshPlanets() {
        getPlanets(API_URL);
    }

    // =============================================================================================
    // Response Parsers
    // =============================================================================================

    private void parsePlanetListResponse(final JSONObject response) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                DaoSession daoSession = ((StarWarsApp) activity.getApplication()).getDaoSession();
                try {
                    JSONArray jsonArrayPlanets = response.getJSONArray("results");
                    List<Planet> planetList = new ArrayList<>();

                    //loop through all planets
                    for (int i = 0; i < jsonArrayPlanets.length()-1; i++) {
                        JSONObject planetJson = jsonArrayPlanets.getJSONObject(i);
                        Planet planet = new Planet(planetJson);
                        planetList.add(planet);
                        daoSession.insertOrReplace(planet);
                    }

                    starWarsView.setPlanets(planetList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(runnable);
    }
}
