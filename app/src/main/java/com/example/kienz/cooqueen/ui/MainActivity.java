package com.example.kienz.cooqueen.ui;


import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kienz.cooqueen.R;
import com.example.kienz.cooqueen.adapter.MyPagerAdapter;
import com.recombee.api_clients.RecombeeClient;
import com.recombee.api_clients.api_requests.AddDetailView;
import com.recombee.api_clients.api_requests.RecommendItemsToUser;
import com.recombee.api_clients.bindings.Recommendation;
import com.recombee.api_clients.bindings.RecommendationResponse;
import com.recombee.api_clients.exceptions.ApiException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;
import model.PlannedResepV3;
import model.ResepV2;
import model.User;
import util.Constants;

public class MainActivity extends AppCompatActivity implements tab1.OnFragmentInteractionListener,tab2.OnFragmentInteractionListener,tab3.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {

    //      =========ALIF
//    public ArrayList<Resepop> mRecipes = new ArrayList<>();
//    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    //      =============
    private Realm realm;
    private User profile;
    @BindView(R.id.nav_view)
    NavigationView mNavigation;
//    @BindView(R.id.userName)
//    TextView navUsername;
//    @BindView(R.id.userEmail)
//    TextView navUseremail;
    TextView navUsername;
    TextView navUseremail;
    private ArrayList<ResepV2> plannedRecipes = new ArrayList<>();
    ArrayList<String> listRecipeId = null;


    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        super.onStop();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new SyncRecommendation().execute();
        ButterKnife.bind(this);
        View headerView =  mNavigation.getHeaderView(0);
        navUsername = (TextView) headerView.findViewById(R.id.userName);
        navUseremail = (TextView) headerView.findViewById(R.id.userEmail);
        setUp();
//        getMyProfile();
//        TeSTING Query

//
//        Log.d("realm",realm.getPath());


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
//        getPlannedRecipes();

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

    private class SyncRecommendation extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... ids) {
            RecombeeClient client = new RecombeeClient("pkmcooking","f7TmuRpKNXlVVNLz6Se5CfSjbSTBRVaPRN6eqZvTPSftZUdAvHuWe9luZCjnynzf");
            try {
                RecommendationResponse recommendationResponse = client.send(new RecommendItemsToUser(SyncUser.current().getIdentity(), 5));
                System.out.println("Recommended items:");
                for(Recommendation rec: recommendationResponse) {
                    System.out.println(rec.getId());
                    listRecipeId.add(rec.getId());
                }
            } catch (ApiException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

//    private void getMyProfile(){
//        String url = Constants.REALM_USER;
//        SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), url).build();
//        realm = Realm.getInstance(config);
//        RealmResults<User> result = realm.where(User.class).findAllAsync();
//        Log.d("hanuwa1", String.valueOf(result.size()));
//        result.addChangeListener((user, changeSet) -> {
//            if(!user.isEmpty()){
//                profile=user.first();
//                navUseremail.setText(profile.getEmail());
//                navUsername.setText(profile.getName());
//            }
//            Log.d("hanuwa1","waki");
//        });
//        if(profile==null){
//            if(!result.isEmpty()){
//                profile=result.first();
//                navUseremail.setText(profile.getEmail());
//                navUsername.setText(profile.getName());
//                Log.d("hanuwa1","waka");
//            }
//            Log.d("hanuwa1","wako");
//        }
//        Log.d("hanuwa1","wake");
//    }

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
//                for (Resepop h : mRecipes) {
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
                    finish();
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

        if (id == R.id.nav_manage) {
            // Handle the camera action
        } else if (id == R.id.nav_home) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    private ResepV2 getRecipe(String recipeId) {
        SyncConfiguration configuration = SyncUser.current()
                .createConfiguration(Constants.REALM_DEFAULT)
                .build();
        realm = Realm.getInstance(configuration);
        return realm
                .where(ResepV2.class)
                .equalTo("recipeId",recipeId)
                .findAllAsync().first();
    }

    private void setUp(){
        String url = Constants.REALM_USER;
        SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), url).build();
        realm = Realm.getInstance(config);
        RealmResults<User> results = realm.where(User.class).findAllAsync();
        results.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<User>>() {
            @Override
            public void onChange(RealmResults<User> users, OrderedCollectionChangeSet changeSet) {
                if(!users.isEmpty()){
                    profile = users.first();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            navUseremail.setText(profile.getEmail());
                            navUsername.setText(profile.getName());
                        }
                    });
                }
            }
        });
        if(profile==null){
            if(!results.isEmpty()){
                profile = results.first();
            }
        }
        if (profile != null) {
            navUseremail.setText(profile.getEmail());
            navUsername.setText(profile.getName());
        }
    }

//    private void GatherRecipes(String que){
//        RealmResults<ResepV2> plannedReseps;
//        if(internet_connection()){
//            try{
//                //TODO
//                plannedReseps = getRecipes(que);
//                plannedReseps.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<ResepV2>>() {
//                    @Override
//                    public void onChange(RealmResults<ResepV2> resepV2s, OrderedCollectionChangeSet changeSet) {
//                        plannedRecipes.addAll(realm.copyFromRealm(resepV2s));
//                        Log.d("hanuwa","wakuna");
//                    }
//                });
////                  getRecipesbyName(que);
//            }catch (NullPointerException e){
//                Log.d("hanuwa",e.getMessage());
//            }
//        }else{
//            final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
//                    "Tidak ada Koneksi Internet.",
//                    Snackbar.LENGTH_SHORT);
//            snackbar.setActionTextColor(ContextCompat.getColor(getApplicationContext(),
//                    R.color.colorPrimary));
//            snackbar.show();
//
//        }
//    }

    void getPlannedRecipes(){
        ArrayList<PlannedResepV3> plannedIds = new ArrayList<>();
        RealmResults<PlannedResepV3> plannedresults = realm.where(PlannedResepV3.class).findAllAsync();
        plannedresults.addChangeListener((plannedResepV2s, changeSet) -> {
            if(changeSet.isCompleteResult()){
                if(!plannedResepV2s.isEmpty()){
                    plannedIds.addAll(plannedresults);
                }
            }
            Log.d("hakuwasize3", String.valueOf(plannedresults.size()));
            for (PlannedResepV3 itemResep : plannedIds){
                plannedRecipes.add(getRecipe(itemResep.getRecipeID()));
            }
            Log.d("hakuwasize4", String.valueOf(plannedRecipes.size()));
        });
        if (plannedIds==null){
            if(!plannedresults.isEmpty()){
                plannedIds.addAll(plannedresults);
                Log.d("hakuwasize2", String.valueOf(plannedresults.size()));
                for (PlannedResepV3 itemResep : plannedIds){
                    plannedRecipes.add(getRecipe(itemResep.getRecipeID()));
                }
            }
        }
        Log.d("hakuwasize1", String.valueOf(plannedresults.size()));
    }

    boolean internet_connection() {
        //Check if connected to internet, output accordingly
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

}

