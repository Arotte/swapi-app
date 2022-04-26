package com.zonal.starwars.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zonal.starwars.R;
import com.zonal.starwars.model.Planet;
import com.zonal.starwars.presenter.StarWarsPresenter;
import com.zonal.starwars.presenter.StarWarsPresenterImpl;

import java.util.List;


public class StarWarsActivity extends AppCompatActivity implements StarWarsView {

    private StarWarsPresenter starWarsPresenter;
    private RecyclerView recyclerView;
    private StarWarsAdapter starWarsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starwars);
        recyclerView = findViewById(R.id.recyclerViewPlanets);
        setAdapter();

        starWarsPresenter = new StarWarsPresenterImpl(this, this);
    }

    private void setAdapter(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        starWarsAdapter = new StarWarsAdapter(this);
        recyclerView.setAdapter(starWarsAdapter);
    }

    @Override
    public void setPlanets(List<Planet> planetList) {
        starWarsAdapter.setPlanetList(planetList);
    }
}
