//////////////////////////////////////////////////////////////
// MapController.java  response to web requests            //
// ver 1.0                                                  //
//                                                          //
//////////////////////////////////////////////////////////////
/*
 * This package provides one Java class MapController
 * which is responsible for the front end request from the
 * map page.
 *
 * */
package com.se.fishbook.controller;

 import com.se.fishbook.model.Post;
 import com.se.fishbook.service.PostService;
 import com.se.fishbook.test.Location;
 import javafx.geometry.Pos;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/map")
public class MapController {
     @Autowired
     private PostService postService;
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
    @ResponseBody
    @RequestMapping(value = "/getData", method = RequestMethod.POST)
    public List<Post> getData(Location location) {
        System.out.println("==============getData");
        List<Location> results = new ArrayList<>();
        List<Post> allPosts = postService.selectAll();
        return allPosts;
    }



}
