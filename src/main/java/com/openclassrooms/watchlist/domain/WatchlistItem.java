package com.openclassrooms.watchlist.domain;

import com.openclassrooms.watchlist.validation.BadMovie;
import com.openclassrooms.watchlist.validation.GoodMovie;
import com.openclassrooms.watchlist.validation.Priority;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//* class level (cross-field validation) validation annotations
@BadMovie
@GoodMovie
//@GroupSequence(groups = {FieldChecks.class, ClassChecks.class})
//@ColumnNameUnique(groups = ClassChecks.class)
public class WatchlistItem {
    public static int index = 0;

    private Integer id;
    @NotBlank(message = "Please enter the title")
    private String title;
    @NotBlank(message = "Please enter the rating")
    private String rating;
    @Priority
    @NotBlank(message = "Please enter the priority")
    private String priority;
    @Size(max=50, message = "Comment should be maximum 50 characters")
    @NotBlank(message = "Please enter the comment")
    private String comment;



    public WatchlistItem() {

    }

    //todo which constructors do I need and when are they called?
    public WatchlistItem(Integer id, String title, String rating, String priority, String comment) {

        this.id = id;
        this.title = title;
        this.rating = rating;
        this.priority = priority;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
