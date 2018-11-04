package model;

public class Meal_Plan {
    private String type;
    private String image_url;
    private String name;

    public Meal_Plan(String type, String image_url, String name) {
        this.type = type;
        this.image_url = image_url;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getName() {
        return name;
    }
}
