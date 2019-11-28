package com.openclassrooms.watchlist.service;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.exception.DuplicateTitleException;
import com.openclassrooms.watchlist.repository.WatchlistitemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {

    /**
    *! having these lines of code makes the WatchlistService class tightly coupled with the Watchlist*
    *! having the new keyword, which instantiates an instance of this classes dependencies makes the classes tightly coupled to each other because we are instantiating a real instance of the dependencies.
     *! which makes this class difficult to unit test
     *! there is no way to replace the real instance with a mock at the time of unit testing because when we create an instance of this class, we are creating a real instance of the dependencies
     */
//    private WatchlistitemRepository watchlistitemRepository =  new WatchlistitemRepository();
//    private MovieRatingService movieRatingService = new MovieRatingService();

    @Autowired
    private WatchlistitemRepository watchlistitemRepository;
    @Autowired
    private MovieRatingService movieRatingService;

//    @Autowired
//    WatchlistService(WatchlistitemRepository watchlistitemRepository, MovieRatingService movieRatingService){
//        this.watchlistitemRepository = watchlistitemRepository;
//        this.movieRatingService = movieRatingService;
//    }


    public List<WatchlistItem> getWatchlist (){
        return watchlistitemRepository.getList();
    }

    public int getWatchlistSize(){
        return watchlistitemRepository.getList().size();
    }

    public void addOrUpdateWatchlistItems(WatchlistItem watchlistItem) throws DuplicateTitleException{
        if(watchlistItem.getId()==null){
            if (watchlistitemRepository.findByTitle(watchlistItem.getTitle())!=null){
                throw new DuplicateTitleException();
            }

            String rating = movieRatingService.getMovieRating(watchlistItem.getTitle());

            //not sure why rating is empty when API returns movie not found message
            if (rating!= null &&  !rating.equals("")){
                watchlistItem.setRating(rating);
            }

            watchlistitemRepository.addItem(watchlistItem);
        }
        else{
            WatchlistItem existingWatchlistItem = watchlistitemRepository.findWatchlistItemById(watchlistItem.getId());
            watchlistitemRepository.updateItem(watchlistItem, existingWatchlistItem);
        }
    }

    public WatchlistItem findWatchlistItembyId(Integer id){
        return watchlistitemRepository.findWatchlistItemById(id);
    }


}
