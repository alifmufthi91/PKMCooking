package model;


import java.util.ArrayList;

public class Recommender {
    String title;
    ArrayList<Resepop> recipeList;

    public Recommender(String title, ArrayList<Resepop> recipeList) {
        this.title = title;
        this.recipeList = recipeList;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Resepop> getRecipeList() {
        return recipeList;
    }
}
