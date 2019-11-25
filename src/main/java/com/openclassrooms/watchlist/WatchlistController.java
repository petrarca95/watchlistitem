package com.openclassrooms.watchlist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WatchlistController {

    private List<WatchlistItem> watchlistItems = new ArrayList<>();


    @RequestMapping(method = RequestMethod.GET, value ="/")
    public ModelAndView getIndex(){
        Map<String, Integer > model = new HashMap<>();

        String viewName = "home";

        return new ModelAndView(viewName, model);
    }

    @RequestMapping(method = RequestMethod.GET, value ="/watchlist")
    public ModelAndView getWatchlistItems(){


        Map<String, Object > model = new HashMap<>();

        model.put("watchlistItems", watchlistItems);
        model.put("numberOfMovies",watchlistItems.size());

        String viewName = "watchlist";

        return new ModelAndView(viewName, model);
    }

    @RequestMapping(method = RequestMethod.GET, value ="/watchlistItemForm")
    public ModelAndView showWatchListItemForm(@RequestParam(required = false) Integer id){

        Map<String, Object > model = new HashMap<>();
         WatchlistItem watchlistItemFound = WatchlistUtil.findWatchlistItemById(id, watchlistItems);
        if (watchlistItemFound== null){
           model.put("watchlistItem", new WatchlistItem());
       }
       else{
           model.put("watchlistItem", watchlistItemFound);
       }

        String viewName = "watchlistItemForm";

        return new ModelAndView(viewName, model);
    }


    /**
     ** we only want to increment the index for a movie that is new, not after we update an existing movie. New movies will have an ID of null until they are set
     ** in order to receive a watchlistItem with an ID field, we need to specify we want to send the id : <input type = "hidden" th:field="*{id}" /> inside the POST form in the html
     */

    @RequestMapping(method = RequestMethod.POST, value ="/watchlistItemForm")
    public ModelAndView submitWatchlistItems(@Valid WatchlistItem watchlistItem, BindingResult bindingResult){


        if (bindingResult.hasErrors()){
            //* how is the object bindingResults, which contains the error messages, getting sent to the view? apparently it is being sent implicitly since errors are correctly populating in the view
            return new ModelAndView("watchlistItemForm");
        }


        if (itemAlreadyExistsValidation(watchlistItem.getTitle())){
            //* rejectValue registers a field error for the specified field of the current object (probably the one that was annotated with @Valid) using the given error description, null is needed to make error a global one
            bindingResult.rejectValue(null,"title", "this movie is already on your watchlist");
            return new ModelAndView("watchlistItemForm");
        }


        //* checking to see if if the item in the form is new (has id ==null) or if it is an existing item being updated
        if (watchlistItem.getId()==null){
            watchlistItem.setId(WatchlistItem.index++);
            watchlistItems.add(watchlistItem);
        }
        //* updating functionality
        else{
            WatchlistItem existingWatchlistItem = WatchlistUtil.findWatchlistItemById(watchlistItem.getId(), watchlistItems);
            //* existingWatchlistItem already contains a reference to the actual object in the arraylist, no need to remove or add objects from array list when updating
            existingWatchlistItem.setTitle(watchlistItem.getTitle());
            existingWatchlistItem.setComment(watchlistItem.getComment());
            existingWatchlistItem.setPriority(watchlistItem.getPriority());
            existingWatchlistItem.setRating(watchlistItem.getRating());
        }



        //* redirecting the view after object is created or updated. This is done by convention so users can see changes right away.
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/watchlist");
        return new ModelAndView(redirectView);
    }



private boolean itemAlreadyExistsValidation(String string){
        for (WatchlistItem watchlistItem: watchlistItems){
            if (string.equals(watchlistItem.getTitle())){
                return true;
            }
        }
    return false;
}


}
