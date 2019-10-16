package com.openclassrooms.watchlist;

public class Watchlistitem {
    public static int index = 0;

    private String title;
    private String rating;
    private String priority;
    private String comment;



    public Watchlistitem() {

    }

    public Watchlistitem(String title, String rating, String priority, String comment) {
        this.title = title;
        this.rating = rating;
        this.priority = priority;
        this.comment = comment;
    }

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        Watchlistitem.index = index;
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
