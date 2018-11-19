package model;


import java.util.ArrayList;

public class Recommender {
    String title;
    ArrayList<Resepi> recipeList;

    public Recommender(String title, ArrayList<Resepi> recipeList) {
        this.title = title;
        this.recipeList = recipeList;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Resepi> getRecipeList() {
        return recipeList;
    }
}
