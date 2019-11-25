package com.openclassrooms.watchlist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BadMovieValidator implements ConstraintValidator<BadMovie, WatchlistItem> {
    @Override
    public boolean isValid(WatchlistItem watchlistItem, ConstraintValidatorContext constraintValidatorContext) {
        if (Integer.parseInt(watchlistItem.getRating())<6 && watchlistItem.getComment().length()<15 ) {
            return false;
        } else
            return true;
    }


}
