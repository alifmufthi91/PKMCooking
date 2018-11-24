//package services;
//
//
//
///**
// * Created by Guest on 4/28/16.
// */
//public class FoodService {
//    public static final String TAG = FoodService.class.getSimpleName();
//
//    public static void findRecipes(String ingredient1, String ingredient2, Callback callback) {
//        String APP_KEY = Constants.APP_KEY;
//        String APP_ID = Constants.APP_ID;
//        OkHttpClient client = new OkHttpClient.Builder().build();
//        String ingredients = (ingredient1 + "," + ingredient2).replaceAll("\\s","");
//
//        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL).newBuilder();
//        urlBuilder.addQueryParameter(Constants.QUERY_PARAMETER, ingredients);
//        urlBuilder.addQueryParameter(Constants.APP_QUERY_PARAMETER, APP_ID);
//        urlBuilder.addQueryParameter(Constants.KEY_QUERY_PARAMETER, APP_KEY);
//
//        String url = urlBuilder.build().toString();
//        Log.v(TAG, url);
//
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//        Call call = client.newCall(request);
//        call.enqueue(callback);
//    }
//
//
//    public ArrayList<ResepV2> processResults(Response response) {
//        ArrayList<ResepV2> recipes = new ArrayList<>();
//
//        try {
//            String jsonData = response.body().string();
//            if (response.isSuccessful()) {
//                JSONObject returnJSON = new JSONObject(jsonData);
//                JSONArray recipesJSON = returnJSON.getJSONArray("hits");
//                for (int i = 0; i < 10; i++) {
//                    JSONObject recipeArrayJSON = recipesJSON.getJSONObject(i);
//                    JSONObject recipeJSON = recipeArrayJSON.getJSONObject("recipe");
//                    String name = recipeJSON.getString("label");
//                    String imageUrl = recipeJSON.getString("image");
//                    String sourceUrl = recipeJSON.getString("url");
//                    String ingredients = recipeJSON.getJSONArray("ingredientLines").toString();
//
//                    ResepV2 recipe = new ResepV2 (name, imageUrl, sourceUrl, ingredients);
//                    recipes.add(recipe);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return recipes;
//    }
//}
