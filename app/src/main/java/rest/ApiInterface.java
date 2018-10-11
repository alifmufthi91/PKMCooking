package rest;

import java.util.List;

import model.Recipe;
import model.Test_Hero;
import model.Params;
import model.example;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

//    TESTING PRADIKA==================================================================================

    String BASE_URL = "https://simplifiedcoding.net/demos/";
    String BASE_URL2 = "https://api.edamam.com/";
    @GET("marvel")
    Call<List<Test_Hero>> getheroes();

//    TESTING PRADIKA==================================================================================


    @GET("search?app_id=3f19beb1&app_key=790a776d229d75fbf14907274c5410dd&")
    Call<example> doGetRecipeList(@Query("q") String... group);

}
