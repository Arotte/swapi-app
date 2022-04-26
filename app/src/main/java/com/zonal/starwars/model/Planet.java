package com.zonal.starwars.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

@Entity(active = true, generateConstructors = true, generateGettersSetters = true)
public class Planet implements Serializable {
    // unique id for serializable
    private static final long serialVersionUID = 7526472295622776147L;

    @Id
    private Long id;

    private String name;
    private String population;
    private String url;
    private String climate;
    private String gravity;
    private String terrain;
    private String orbitalPeriod;
    private String diameter;
    private String rotationPeriod;
    private String surfaceWater;
    private String created;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 547733003)
    private transient PlanetDao myDao;

    public Planet(JSONObject planetJson) {
        try {
            setName(planetJson.getString("name"));
            setPopulation(planetJson.getString("population"));
            setUrl(planetJson.getString("url"));
            setClimate(planetJson.getString("climate"));
            setGravity(planetJson.getString("gravity"));
            setTerrain(planetJson.getString("terrain"));
            setOrbitalPeriod(planetJson.getString("orbital_period"));
            setDiameter(planetJson.getString("diameter"));
            setRotationPeriod(planetJson.getString("rotation_period"));
            setSurfaceWater(planetJson.getString("surface_water"));
            setCreated(planetJson.getString("created"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Generated(hash = 720757740)
    public Planet() {
    }

    @Generated(hash = 1235918115)
    public Planet(Long id, String name, String population, String url, String climate,
            String gravity, String terrain, String orbitalPeriod, String diameter,
            String rotationPeriod, String surfaceWater, String created) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.url = url;
        this.climate = climate;
        this.gravity = gravity;
        this.terrain = terrain;
        this.orbitalPeriod = orbitalPeriod;
        this.diameter = diameter;
        this.rotationPeriod = rotationPeriod;
        this.surfaceWater = surfaceWater;
        this.created = created;
    }

    // ===========================================================
    // Default getters and setters
    // ===========================================================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(String orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getRotationPeriod() {
        return rotationPeriod;
    }

    public void setRotationPeriod(String rotationPeriod) {
        this.rotationPeriod = rotationPeriod;
    }

    public String getSurfaceWater() {
        return surfaceWater;
    }

    public void setSurfaceWater(String surfaceWater) {
        this.surfaceWater = surfaceWater;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1300886363)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPlanetDao() : null;
    }
}
