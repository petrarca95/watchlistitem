package com.openclassrooms.watchlist.validation;

import com.openclassrooms.watchlist.domain.WatchlistItem;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GoodMovieValidator implements ConstraintValidator<GoodMovie, WatchlistItem> {
    @Override
    public boolean isValid(WatchlistItem value, ConstraintValidatorContext constraintValidatorContext) {
        return !(Double.parseDouble(value.getRating()) >= 8 &&  "L".equals(value.getPriority()));
    }
}
