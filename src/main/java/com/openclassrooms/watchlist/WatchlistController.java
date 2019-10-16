package com.openclassrooms.watchlist;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WatchlistController {

    private ArrayList<Watchlistitem> watchlistitems;


    @RequestMapping(method = RequestMethod.GET, value ="/")
    public ModelAndView getWatchlistItem(){
        Map<String, Integer> model = new HashMap<>();


        watchlistitems.add(new Watchlistitem());
        watchlistitems.add(new Watchlistitem());
        watchlistitems.add(new Watchlistitem());
        watchlistitems.add(new Watchlistitem());



        model.put("obj", watchlistitems.size());

        String viewName = "index";

        return new ModelAndView(viewName, model);
    }




}
