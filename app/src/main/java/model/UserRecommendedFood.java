package model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class UserRecommendedFood extends RealmObject {
    @PrimaryKey
    @Required
    private String recommendationId;
    private String userId;
    private RealmList<String> listFoodId = new RealmList<>();

    public UserRecommendedFood() {
    }

    public UserRecommendedFood(String recommendationId) {
        this.recommendationId = recommendationId;
    }

    public UserRecommendedFood(String recommendationId, String userId, RealmList<String> listFoodId) {
        this.recommendationId = recommendationId;
        this.userId = userId;
        this.listFoodId = listFoodId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(String recommendationId) {
        this.recommendationId = recommendationId;
    }

    public RealmList<String> getListFoodId() {
        return listFoodId;
    }

    public void setListFoodId(RealmList<String> listFoodId) {
        this.listFoodId = listFoodId;
    }
}
