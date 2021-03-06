package com.openclassrooms.watchlist.service.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.openclassrooms.watchlist.service.MovieRatingService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//* service acts as a client for the OMDB API


//@ConditionalOnProperty(name = "app.environment", havingValue = "prod")
@Service
@Profile("prod")
public class MovieRatingServiceLiveImpl implements MovieRatingService {

    String apiUrl = "http://www.omdbapi.com/?apikey=ac58dccb&t=";

    @Override
    public String getMovieRating(String title) {

        try {
            RestTemplate template = new RestTemplate();

            //** getForEntity used to make a GET request
            ResponseEntity<ObjectNode> response =
                    template.getForEntity(apiUrl + title, ObjectNode.class);

            ObjectNode jsonObject = response.getBody();


            return jsonObject.path("imdbRating").asText();
        } catch(NullPointerException e){
            System.out.println("No rating available for this movie");
            return null;
        }
        catch (Exception e) {
            System.out.println("Something went wrong while calling OMDb API" + e.getMessage());
            return null;
        }
    }

}
