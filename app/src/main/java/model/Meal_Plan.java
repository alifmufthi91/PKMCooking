package model;

public class Meal_Plan {
    private String type;
    private String planId;
    private ResepV2 resep;

    public void setType(String type) {
        this.type = type;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public Meal_Plan(String type, ResepV2 resep, String planId) {
        this.type = type;
        this.resep = resep;
        this.planId = planId;

    }

    public String getType() {
        return type;
    }

    public String getImage_url() {
        return resep.getImageUrl();
    }

    public String getName() {
        return resep.getName();
    }

    public ResepV2 getResep() {
        return resep;
    }

    public void setResep(ResepV2 resep) {
        this.resep = resep;
    }
}
