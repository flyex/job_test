package com.flyex.service;

import com.flyex.mapper.BikeMapper;
import com.flyex.pojo.Bike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BikeServiceImpl implements BikeService {

    @Autowired
    private MongoTemplate mongoTemplate;

//    @Autowired
//    private BikeMapper bikeMapper;
//
//    @Override
//    public void save(Bike bike) {
//        bikeMapper.save(bike);
//    }

    //save to mongo DB
    @Override
    public void save(Bike bike) {
        mongoTemplate.save(bike);
    }

    @Override
    public List<Bike> getAllBike() {
        return mongoTemplate.findAll(Bike.class);
    }

    @Override
    public GeoResults<Bike> findNear(double longitude, double latitude) {
        NearQuery nearQuery = NearQuery.near(longitude, latitude, Metrics.KILOMETERS);

        nearQuery.maxDistance(0.5).query(new Query().addCriteria(Criteria.where("status").is(0)).limit(10));

        GeoResults<Bike> bikes = mongoTemplate.geoNear(nearQuery, Bike.class);

        return bikes;
    }

}
