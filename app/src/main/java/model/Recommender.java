package model;


import java.util.ArrayList;
import java.util.List;

public class Recommender {
    String title;
    ArrayList<Recipe> recipeList;

    public Recommender(String title, ArrayList<Recipe> recipeList) {
        this.title = title;
        this.recipeList = recipeList;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Recipe> getRecipeList() {
        return recipeList;
    }
}
