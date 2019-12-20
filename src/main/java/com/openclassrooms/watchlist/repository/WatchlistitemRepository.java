package com.openclassrooms.watchlist.repository;

import com.openclassrooms.watchlist.domain.WatchlistItem;

import java.util.ArrayList;
import java.util.List;


/**
** Moved list of watchlistitems as instance variable here because that is our "DataBase", added CRUD operations because this is the DAL, also moved the methods of Util here and made them non static, these methods will also be interacting with the "DB"
 */

/**
 ** This is a repository class, which makes up the DAL (Data Access Layer)
 *! We want to keep this layer as thin as possible
 ** Responsibilities:
 ** 1) Class is limited to CRUD operations on a data source, usually a DB (class interacts with the DB)
 ** 2) manipulating Model object, which is then passed to the view component of the presentation layer, remember the presentation layer UI is made up of Model View Controller (MVC)
 ** 3) returning appropriate ModelAndView , view name.
 ** 4) communicate with the layer below, the service/business layer which is made up of Service classes
 */


//@Repository
public class WatchlistitemRepository {

    private List<WatchlistItem> watchlistItems = new ArrayList<>();


    public List<WatchlistItem> getList(){
        return watchlistItems;
    }

    public void addItem(WatchlistItem watchlistItem){
        watchlistItem.setId(WatchlistItem.index++);
        watchlistItems.add(watchlistItem);
    }

    public void updateItem(WatchlistItem watchlistItem, WatchlistItem existingWatchlistItem){
        existingWatchlistItem.setTitle(watchlistItem.getTitle());
        existingWatchlistItem.setComment(watchlistItem.getComment());
        existingWatchlistItem.setPriority(watchlistItem.getPriority());
        existingWatchlistItem.setRating(watchlistItem.getRating());
    }


    public  WatchlistItem findWatchlistItemById(Integer id){
        WatchlistItem watchlistItemFound = null;
        for (WatchlistItem watchlistitem : watchlistItems){
            if (watchlistitem.getId().equals(id)){
                watchlistItemFound = watchlistitem;
                break;
            }
        }
        return watchlistItemFound;

    }

    public Integer findIndexOfWatchlistItem(Integer id){
        Integer location = null;
        int k=0;
        for (WatchlistItem watchlistItem: watchlistItems){
            if (watchlistItem.getId().equals(id)){
                location = k;
                break;
            }
            k++;
        }
        return location;
    }

    public WatchlistItem findByTitle(String title){
        for (WatchlistItem watchlistItem: watchlistItems){
            if (title.equals(watchlistItem.getTitle())){
                return watchlistItem;
            }
        }
        return null;
    }



}
