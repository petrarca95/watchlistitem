package com.openclassrooms.watchlist.validation;

import com.openclassrooms.watchlist.domain.WatchlistItem;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GoodMovieValidator implements ConstraintValidator<GoodMovie, WatchlistItem> {
    @Override
//    ! this is throing error when form is submitted empty because class level/cross field validation seems to be carried out before field validation. Grouping sequence is a work around
    public boolean isValid(WatchlistItem value, ConstraintValidatorContext constraintValidatorContext) {
        return !(Double.parseDouble(value.getRating()) >= 8 &&  "L".equals(value.getPriority()));
    }
}
