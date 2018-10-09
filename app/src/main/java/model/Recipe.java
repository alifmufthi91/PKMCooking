package model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recipe {

    @SerializedName("uri")
    @Expose
    public String uri;
    @SerializedName("label")
    @Expose
    public String label;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("source")
    @Expose
    public String source;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("yield")
    @Expose
    public Double yield;
    @SerializedName("dietLabels")
    @Expose
    public List<Object> dietLabels = null;
    @SerializedName("healthLabels")
    @Expose
    public List<String> healthLabels = null;
    @SerializedName("cautions")
    @Expose
    public List<String> cautions = null;
    @SerializedName("ingredientLines")
    @Expose
    public List<String> ingredientLines = null;
    @SerializedName("calories")
    @Expose
    public Double calories;
    @SerializedName("totalWeight")
    @Expose
    public Double totalWeight;
    @SerializedName("totalTime")
    @Expose
    public Double totalTime;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getYield() {
        return yield;
    }

    public void setYield(Double yield) {
        this.yield = yield;
    }

    public List<Object> getDietLabels() {
        return dietLabels;
    }

    public void setDietLabels(List<Object> dietLabels) {
        this.dietLabels = dietLabels;
    }

    public List<String> getHealthLabels() {
        return healthLabels;
    }

    public void setHealthLabels(List<String> healthLabels) {
        this.healthLabels = healthLabels;
    }

    public List<String> getCautions() {
        return cautions;
    }

    public void setCautions(List<String> cautions) {
        this.cautions = cautions;
    }

    public List<String> getIngredientLines() {
        return ingredientLines;
    }

    public void setIngredientLines(List<String> ingredientLines) {
        this.ingredientLines = ingredientLines;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }
}