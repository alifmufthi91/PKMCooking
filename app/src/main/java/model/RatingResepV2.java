package model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class RatingResepV2 extends RealmObject {
    @PrimaryKey
    @Required
    private String idRating;
    @Required
    private String UserID;
    @Required
    private String recipeID;
    @Required
    private Double rating;

    public RatingResepV2() {
    }

    public RatingResepV2(String idRating, String userID, String recipeID, Double rating) {
        this.idRating = idRating;
        UserID = userID;
        this.recipeID = recipeID;
        this.rating = rating;
    }

    public String getIdRating() {
        return idRating;
    }

    public void setIdRating(String idRating) {
        this.idRating = idRating;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(String recipeID) {
        this.recipeID = recipeID;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
