package com.se.fishbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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



}
