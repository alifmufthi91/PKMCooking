package model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class VeganRecommendedFood extends RealmObject {
    @PrimaryKey
    @Required
    private String recommendationId;

    private RealmList<ResepV2> listFood = new RealmList<>();

    public VeganRecommendedFood() {
    }

    public VeganRecommendedFood(String recommendationId, RealmList<ResepV2> listFood) {
        this.recommendationId = recommendationId;
        this.listFood = listFood;
    }

    public String getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(String recommendationId) {
        this.recommendationId = recommendationId;
    }

    public RealmList<ResepV2> getListFood() {
        return listFood;
    }

    public void setListFood(RealmList<ResepV2> listFood) {
        this.listFood = listFood;
    }
}
