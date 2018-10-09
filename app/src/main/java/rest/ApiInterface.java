package rest;

import model.Params;
import model.example;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("search")
    Call<example> doGetListRecipe();

    @GET("search?")
    Call<Params> doGetRecipeList(@Query("q") String q,@Query("app_key") String appKey,@Query("app_id") String appId);


}
