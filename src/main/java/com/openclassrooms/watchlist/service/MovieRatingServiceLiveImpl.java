package com.openclassrooms.watchlist.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

//* service acts as a client for the OMDB API

//@Service("MovieRatingServiceLiveImpl")
//@Service
//@Profile("prod")
@Slf4j
public class MovieRatingServiceLiveImpl implements MovieRatingService {

    String apiUrl = "http://www.omdbapi.com/?apikey=ac58dccb&t=";

    @Override
    public String getMovieRating(String title) {

        try {
            RestTemplate template = new RestTemplate();

            log.debug("Calling OMdAPI with url "+apiUrl+" to retrieve info of movie with title "+title);


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
