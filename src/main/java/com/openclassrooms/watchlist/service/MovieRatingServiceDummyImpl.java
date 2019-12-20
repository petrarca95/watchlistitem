package com.openclassrooms.watchlist.service;

//@Profile("dev")
//@Service
public class MovieRatingServiceDummyImpl implements MovieRatingService {

    @Override
    public String getMovieRating(String title) {
        return "9";
    }
}
