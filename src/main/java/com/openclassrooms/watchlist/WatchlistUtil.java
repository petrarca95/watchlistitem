package com.openclassrooms.watchlist;

import java.util.List;

public class WatchlistUtil {



    public static WatchlistItem findWatchlistItemById(Integer id, List<WatchlistItem> watchlistItems){
        WatchlistItem watchlistItemFound = null;
        for (WatchlistItem watchlistitem : watchlistItems){
            if (watchlistitem.getId().equals(id)){
                watchlistItemFound = watchlistitem;
                break;
            }
        }
        return watchlistItemFound;

    }

    public static Integer findIndexOfWatchlistItem(Integer id, List<WatchlistItem>watchlistItems){
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
}
