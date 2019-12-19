package com.openclassrooms.watchlist.service.impl;

import com.openclassrooms.watchlist.service.MovieRatingService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

//@ConditionalOnProperty(name = "app.environment", havingValue = "dev")
@Service
@Profile("dev")
public class MovieRatingServiceDummyImpl implements MovieRatingService {


    @Override
    public String getMovieRating(String title) {
        String placeHolder = "9";
        return placeHolder;
    }
}
