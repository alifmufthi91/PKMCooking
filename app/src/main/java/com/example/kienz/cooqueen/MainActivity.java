package com.example.kienz.cooqueen;




import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import model.Params;
import model.Recipe;
import model.Test_Hero;
import model.example;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements tab1.OnFragmentInteractionListener,tab2.OnFragmentInteractionListener,tab3.OnFragmentInteractionListener {


    ApiInterface apiInterface;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);


//        ================================
        retrofittest();
//        ================================



    }


//        TESTING PRADIKA===========================================================================================================================================
    public void retrofittest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<example> call = api.doGetRecipeList("lontong");
        call.enqueue(new Callback<example>() {
            @Override
            public void onResponse(Call<example> call, Response<example> response) {

                example heroes = response.body();
                Log.d("statuspaket","yes");

            }

            @Override
            public void onFailure(Call<example> call, Throwable t) {
                Log.d("statuspaket","no");
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
            }


        });
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
    }
//        TESTING PRADIKA===========================================================================================================================================


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
