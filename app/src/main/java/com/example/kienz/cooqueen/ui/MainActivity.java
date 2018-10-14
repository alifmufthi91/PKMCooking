package com.example.kienz.cooqueen.ui;


import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.example.kienz.cooqueen.R;
import com.example.kienz.cooqueen.adapter.MyPagerAdapter;
import java.io.IOException;
import java.util.ArrayList;
import butterknife.ButterKnife;
import model.Recipe;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import services.FoodService;

public class MainActivity extends AppCompatActivity implements tab1.OnFragmentInteractionListener,tab2.OnFragmentInteractionListener,tab3.OnFragmentInteractionListener {

    //      =========ALIF
    public ArrayList<Recipe> mRecipes = new ArrayList<>();
//    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    //      =============


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();


//        ================================
        getRecipes("flour", "chicken");
//        ================================


    }

    private void init(){
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void getRecipes(String ingredient1, String ingredient2) {
        final FoodService foodService = new FoodService();
        foodService.findRecipes(ingredient1, ingredient2, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("statmsg","no");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                Log.d("statmsg","yes");
                mRecipes = foodService.processResults(response);
                for (Recipe h : mRecipes) {
                    Log.d("nama",h.getName());
                    Log.d("urlgambar",h.getImageUrl());
                    Log.d("sumber",h.getSourceUrl());
                }
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds model.hits to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // Action goes here
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


//        TESTING PRADIKA===========================================================================================================================================
//    public void retrofittest() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(apiInterface.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ApiInterface api = retrofit.create(ApiInterface.class);
//        Call<List<Test_Hero>> call = api.getheroes();
//        call.enqueue(new Callback<List<Test_Hero>>() {
//            @Override
//            public void onResponse(Call<List<Test_Hero>> call, Response<List<Test_Hero>> response) {
//
////                ResponseEdamamRecipe recipes = response.body();
////                Log.d("statuspaket","yes");
//                if (response.isSuccessful()) {
//                    Log.d("statuspaket","yes");
//                    // todo display the data instead of just a toast
//                }
//                else {
//                    Log.d("statuspaket","fail");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Test_Hero>> call, Throwable t) {
//                Log.d("statuspaket","no");
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
//            }
//
//
//        });
//        final FindRecipes foodService = new FindRecipes();
//        FindRecipes.findRecipes("flour", "chicken", new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) {
//                ArrayList<Resep> mRecipes = new ArrayList<>();
//                mRecipes = foodService.processResults(response);
//                for (Resep h : mRecipes) {
//                    Log.d("nama",h.getName());
//                    Log.d("urlgambar",h.getImageUrl());
//                    Log.d("sumber",h.getSourceUrl());
//                }
//            }
//
//        });
//    }
//        TESTING PRADIKA===========================================================================================================================================


