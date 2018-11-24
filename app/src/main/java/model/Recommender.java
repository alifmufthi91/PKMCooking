package model;


import java.util.ArrayList;

public class Recommender {
    String title;
    ArrayList<ResepV2> recipeList;

    public Recommender(String title, ArrayList<ResepV2> recipeList) {
        this.title = title;
        this.recipeList = recipeList;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<ResepV2> getRecipeList() {
        return recipeList;
    }
}
