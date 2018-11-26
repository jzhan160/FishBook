package com.se.fishbook.controller;

import com.se.fishbook.test.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
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
    public List<JsonResult> getData(JsonResult jsonResult) {
        System.out.println("==============getData");
        System.out.println(jsonResult);
        List<JsonResult> results = new ArrayList<>();
        try {
            /*43.0398832, -76.1330401*/
            //mock data
            //we need locations from database
            JsonResult jsonResult1 = new JsonResult();
            jsonResult1.setLat(43.0398765);
            jsonResult1.setLng(-76.1330451);
            results.add( jsonResult1);
            JsonResult jsonResult2 = new JsonResult();
            jsonResult2.setLat(43.0395765);
            jsonResult2.setLng(-76.1334451);
            results.add(jsonResult2);

            JsonResult jsonResult3 = new JsonResult();
            jsonResult3.setLat(jsonResult.getLat());
            jsonResult3.setLng(jsonResult.getLng());
            results.add( jsonResult3);
        } catch (Exception e) {
            System.out.println("error");
        }
        return results;
    }



}
