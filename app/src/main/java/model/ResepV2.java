package model;


import java.util.ArrayList;
import java.util.Collections;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Guest on 4/28/16.
 */
public class ResepV2 extends RealmObject {
    @PrimaryKey
    @Required
    private String recipeId;

    private String name;
    private String imageUrl;
    private String sourceUrl;
    private String description;
    private int sajian;
    private RealmList<String> instruksi = new RealmList<>();
    private RealmList<String> labels = new RealmList<>();
    private RealmList<String> alat = new RealmList<>();
    private RealmList<String> ingredients = new RealmList<>();
    private double Energy;
    private double Fat;
    private double Fasat;
    private double Sugar;
    private double Carb;
    private double Fiber;
    private double Protein;
    private double Salt;
    private double rating_value;
    private int rating_giver;

    public ResepV2(String name, String imageUrl, String sourceUrl, String description) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.sourceUrl = sourceUrl;
        this.description = description;
        this.rating_value=0;
        this.rating_giver=0;
    };

    public ResepV2() {};

    public ResepV2(String name, String imageUrl, String sourceUrl, String ingredints, String description, int sajian, String instrc, String labels, String alat) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.sourceUrl = sourceUrl;
        this.ingredients = StringtoRealmList(ingredints);
        this.description = description;
        this.sajian = sajian;
        this.instruksi = StringtoRealmList(instrc);
        this.labels = StringtoRealmList(labels);
        this.alat = StringtoRealmList(alat);
        this.rating_value=0;
        this.rating_giver=0;
    }

    public ArrayList<String> getListString(RealmList<String> Src){
        ArrayList<String> Dst = new ArrayList<>();
        Dst.addAll(Src);
        return Dst;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public String getDescription() {
        return description;
    }

    public int getSajian() {
        return sajian;
    }

    public RealmList<String> getInstruksi() {
        return instruksi;
    }

    public RealmList<String> getLabels() {
        return labels;
    }

    public RealmList<String> getAlat() {
        return alat;
    }

    public RealmList<String> getIngredients() {
        return ingredients;
    }

    public double getEnergy() {
        return Energy;
    }

    public double getFat() {
        return Fat;
    }

    public double getFasat() {
        return Fasat;
    }

    public double getSugar() {
        return Sugar;
    }

    public double getCarb() {
        return Carb;
    }

    public double getFiber() {
        return Fiber;
    }

    public double getProtein() {
        return Protein;
    }

    public double getSalt() {
        return Salt;
    }

    public double getRating_value() {
        return rating_value;
    }

    public int getRating_giver() {
        return rating_giver;
    }

    public ArrayList<String> getNutritions(){
        ArrayList<String> nutritions = new ArrayList<>();
        if(this.Energy != 0){
            nutritions.add("Energi : "+String.valueOf(this.Energy)+"kcal");
        }
        if(this.Carb != 0){
            nutritions.add("Karbohidrat : "+String.valueOf(this.Carb)+"g");
        }
        if(this.Fat != 0){
            nutritions.add("Lemak : "+String.valueOf(this.Fat)+"g");
        }
        if(this.Fasat != 0){
            nutritions.add("Lemak Jenuh : "+String.valueOf(this.Fasat)+"g");
        }
        if(this.Sugar != 0){
            nutritions.add("Gula : "+String.valueOf(this.Sugar)+"g");
        }
        if(this.Fiber != 0){
            nutritions.add("Serat : "+String.valueOf(this.Fiber)+"g");
        }
        if(this.Protein != 0){
            nutritions.add("Protein : "+String.valueOf(this.Protein)+"g");
        }
        if(this.Salt != 0){
            nutritions.add("Garam : "+String.valueOf(this.Salt)+"g");
        }
        return nutritions;
    }

    public void setNutrients(double Energy, double Fat, double Fasat, double Sugar, double Carb, double Fiber, double Protein, double Salt){
        this.Energy = Energy;
        this.Fat = Fat;
        this.Fasat = Fasat;
        this.Carb = Carb;
        this.Fiber = Fiber;
        this.Protein = Protein;
        this.Sugar = Sugar;
        this.Salt = Salt;
    }

    public RealmList<String> StringtoRealmList(String ingre) {
        RealmList<String> ingredients= new RealmList<>();
        String[] ingr = ingre.replaceAll("\\[\"","").replaceAll("\"\\]","").replaceAll("\\\\","").split("\",\"");
        Collections.addAll(ingredients, ingr);
        return  ingredients;
    }

    public void addIngredients(String ing){
        this.ingredients.add(ing);
    }



    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public void giveRating(double value){
        if(this.rating_giver==0){
            this.rating_value=value;
        }else{
            this.rating_value=((this.rating_value*this.rating_giver)+value)/(this.rating_giver+1);
        }
        this.rating_giver++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSajian(int sajian) {
        this.sajian = sajian;
    }

    public void setInstruksi(RealmList<String> instruksi) {
        this.instruksi = instruksi;
    }

    public void setLabels(RealmList<String> labels) {
        this.labels = labels;
    }

    public void setAlat(RealmList<String> alat) {
        this.alat = alat;
    }

    public void setIngredients(RealmList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setEnergy(double energy) {
        Energy = energy;
    }

    public void setFat(double fat) {
        Fat = fat;
    }

    public void setFasat(double fasat) {
        Fasat = fasat;
    }

    public void setSugar(double sugar) {
        Sugar = sugar;
    }

    public void setCarb(double carb) {
        Carb = carb;
    }

    public void setFiber(double fiber) {
        Fiber = fiber;
    }

    public void setProtein(double protein) {
        Protein = protein;
    }

    public void setSalt(double salt) {
        Salt = salt;
    }

    public void setRating_value(double rating_value) {
        this.rating_value = rating_value;
    }

    public void setRating_giver(int rating_giver) {
        this.rating_giver = rating_giver;
    }
}