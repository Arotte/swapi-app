package com.zonal.starwars.presenter;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

import com.zonal.starwars.StarWarsApp;
import com.zonal.starwars.model.DaoSession;
import com.zonal.starwars.model.Planet;
import com.zonal.starwars.view.PlanetDetailsView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class PlanetDetailsPresenterImpl implements PlanetDetailsPresenter {
    private PlanetDetailsView planetDetailsView;
    private Activity activity;

    public PlanetDetailsPresenterImpl(PlanetDetailsView planetDetailsView, Activity activity) {
        this.planetDetailsView = planetDetailsView;
        this.activity=activity;
    }

    // =============================================================================================
    // API Interaction methods
    // =============================================================================================

    public void getPlanet(final String urlName) {
        Thread thread =  new Thread() {
            @Override
            public void run() {
                try {
                    URL  url = new URL(urlName);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    url.openStream()));
                    String inputLine;
                    StringBuilder planet = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        planet.append(inputLine);
                    }

                    JSONObject jsonObject = new JSONObject(planet.toString());
                    parsePlanetResponse(jsonObject);

                    in.close();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    @Override
    public void refreshPlanet(String planetUrl) {
        getPlanet(planetUrl);
    }

    // =============================================================================================
    // Response Parsers
    // =============================================================================================

    private void parsePlanetResponse(final JSONObject response) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                DaoSession daoSession = ((StarWarsApp) activity.getApplication()).getDaoSession();
                Planet planet = new Planet(response);
                daoSession.insertOrReplace(planet);
                planetDetailsView.setPlanet(planet);
            }
        };
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(runnable);
    }
}
