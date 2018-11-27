package com.se.fishbook.controller;

 import com.se.fishbook.test.Location;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/*
* MapController
* */

@Controller
@RequestMapping("/map")
public class MapController {

    //direct to the map page
    @RequestMapping("")
    public String map(){
        return "map/Home";
    }

    //click one spot on the map
    //direct to the page which shows all posts around the clicking spot
    @RequestMapping("/search")
    public String search(){
        return "";
    }
    //get post location
    //just for test
    @ResponseBody
    @RequestMapping(value = "/getData", method = RequestMethod.POST)
    public List<Location> getData(Location location) {
        System.out.println("==============getData");
        System.out.println(location);
        List<Location> results = new ArrayList<>();
        try {
            /*43.0398832, -76.1330401*/
            //mock data
            //we need locations from database
            Location location1 = new Location();
            location1.setLat(43.0398765);
            location1.setLng(-76.1330451);
            results.add(location1);
            Location location2 = new Location();
            location2.setLat(43.0395765);
            location2.setLng(-76.1334451);
            results.add(location2);

            Location location3 = new Location();
            location3.setLat(location.getLat());
            location3.setLng(location.getLng());
            results.add( location3);
        } catch (Exception e) {
            System.out.println("error");
        }
        return results;
    }



}
