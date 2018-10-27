package com.example.kienz.cooqueen.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kienz.cooqueen.R;
import com.example.kienz.cooqueen.adapter.MyPagerAdapter;

import butterknife.ButterKnife;
import io.realm.SyncUser;

public class MainActivity extends AppCompatActivity implements tab1.OnFragmentInteractionListener,tab2.OnFragmentInteractionListener,tab3.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {

    //      =========ALIF
//    public ArrayList<Recipe> mRecipes = new ArrayList<>();
//    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    //      =============


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);


//        ================================
//        getRecipes("flour", "chicken");
//        ================================
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


//    private void getRecipes(String ingredient1, String ingredient2) {
//        final FoodService foodService = new FoodService();
//        foodService.findRecipes(ingredient1, ingredient2, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("statmsg","no");
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) {
//                Log.d("statmsg","yes");
//                mRecipes = foodService.processResults(response);
//                for (Recipe h : mRecipes) {
//                    Log.d("nama",h.getName());
//                    Log.d("urlgambar",h.getImageUrl());
//                    Log.d("sumber",h.getSourceUrl());
//                }
//            }
//        });
//    }

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
            case R.id.action_logout:
                // Action goes here
                SyncUser syncUser = SyncUser.current();
                if (syncUser != null) {
                    syncUser.logOut();
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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


