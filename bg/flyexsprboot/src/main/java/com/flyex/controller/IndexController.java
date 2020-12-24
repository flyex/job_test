package com.flyex.controller;

import com.alibaba.fastjson.JSONObject;
import com.flyex.pojo.Bike;
import com.flyex.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Controller
public class IndexController {

    @Autowired
    private BikeService bikeService;


    @RequestMapping("/bike")
    @ResponseBody
    public String index(){
        return "success";
    }

    @RequestMapping("/bike2")
    @ResponseBody
    public String getAndSave(Bike bike){
       // bikeService.save(bike);
        System.out.println(bike);
        return "success";
    }


    @PostMapping("/bike3")
    @ResponseBody
    public String save2Mongo(@RequestBody String bike){
        //将Json对象映射为Bike类
        Bike b = JSONObject.parseObject(bike, Bike.class);
        //System.out.println(b);
        bikeService.save(b);
        return "success";
    }

    @GetMapping("/allBikes")
    @ResponseBody
    public List<Bike> getAllBike(){

        return bikeService.getAllBike();
    }

    @GetMapping("/nearBy")
    @ResponseBody
    public List<Bike> findNear(double longitude,double latitude){
        List<Bike> bikes = new ArrayList<>();

        GeoResults<Bike> geoBikes = bikeService.findNear(longitude, latitude);

        System.out.println(geoBikes);

        List<GeoResult<Bike>> content = geoBikes.getContent();

        ListIterator<GeoResult<Bike>> geoResultListIterator = content.listIterator();
        while (geoResultListIterator.hasNext()){
            GeoResult<Bike> geoResultBike = geoResultListIterator.next();
            Bike bike = geoResultBike.getContent();

            System.out.println(bike);

            bikes.add(bike);
            System.out.println(bikes);
        }

//        Iterator<GeoResult<Bike>> geoResultIterator = content.iterator();
//
//        while (geoResultIterator.hasNext()){
//            GeoResult<Bike> geoResult = geoResultIterator.next();
//            Bike bike = geoResult.getContent();
//            bikes.add(bike);
//        }
        return bikes;
    }

}
