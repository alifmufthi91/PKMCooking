package rest;

import java.util.List;

import model.Test_Hero;
import model.Params;
import model.example;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

//    TESTING PRADIKA==================================================================================

    String BASE_URL = "https://simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<Test_Hero>> getheroes();

//    TESTING PRADIKA==================================================================================

    @GET("search")
    Call<example> doGetListRecipe();

    @GET("search?")
    Call<Params> doGetRecipeList(@Query("q") String q,@Query("app_key") String appKey,@Query("app_id") String appId);


}
