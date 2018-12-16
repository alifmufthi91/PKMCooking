package model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class ViewInteraction extends RealmObject {
    @PrimaryKey
    @Required
    private String idInteraction;
    @Required
    private String userID;
    @Required
    private String recipeID;

    public ViewInteraction() {
    }

    public ViewInteraction(String idInteraction, String userID, String recipeID) {
        this.idInteraction = idInteraction;
        this.userID = userID;
        this.recipeID = recipeID;
    }

    public String getIdInteraction() {
        return idInteraction;
    }

    public void setIdInteraction(String idInteraction) {
        this.idInteraction = idInteraction;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(String recipeID) {
        this.recipeID = recipeID;
    }
}
