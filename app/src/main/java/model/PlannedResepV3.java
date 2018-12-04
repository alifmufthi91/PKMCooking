package model;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class PlannedResepV3 extends RealmObject {
    @PrimaryKey
    @Required
    private String planId;
    @Required
    private String recipeID;
    @Required
    private String type;

    public PlannedResepV3() {
    }

    public PlannedResepV3(String recipeID, String type) {
        this.planId = UUID.randomUUID().toString();
        this.recipeID = recipeID;
        this.type = type;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(String recipeID) {
        this.recipeID = recipeID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
