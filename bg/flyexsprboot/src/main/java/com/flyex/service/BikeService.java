package com.flyex.service;

import com.flyex.pojo.Bike;
import org.springframework.data.geo.GeoResults;

import java.util.List;

public interface BikeService {
    //public void save(Bike bike);
    public void save(Bike bike);

    public List<Bike> getAllBike();

    public GeoResults<Bike> findNear(double longitude, double latitude);
}
