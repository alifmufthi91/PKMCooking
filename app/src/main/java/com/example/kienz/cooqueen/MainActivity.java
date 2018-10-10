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

import java.util.List;

import model.Test_Hero;
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
                .baseUrl(ApiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<List<Test_Hero>> call = api.getheroes();
        call.enqueue(new Callback<List<Test_Hero>>() {
            @Override
            public void onResponse(Call<List<Test_Hero>> call, Response<List<Test_Hero>> response) {
                List<Test_Hero> heroes = response.body();
                for (Test_Hero h : heroes) {
                    Log.d("realname", h.getRealname());
                    Log.d("imageurl", h.getImageurl());
                    Log.d("createdby", h.getCreatedby());

                }
            }
            @Override
            public void onFailure(Call<List<Test_Hero>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
            }
        });
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
